package com.example.notetaking.Controllers;

import com.example.notetaking.Entity.Note;
import com.example.notetaking.Indexer;
import com.example.notetaking.JwtAuthentication.Repositories.RoleRepository;
import com.example.notetaking.JwtAuthentication.Repositories.UserRepository;
import com.example.notetaking.Repositories.CategoryRepository;
import com.example.notetaking.Repositories.NotetakingRepository;
import com.example.notetaking.Services.NoteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(NotetakingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NotetakingControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    //@MockBean
    private NoteServiceImpl noteservice;

    @MockBean
    private NotetakingRepository noteRepository;

    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    Indexer index;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleRepository roleRepository;
    private Pageable paging;
    private Page<Note> notesPage;

    List<Note> notes = new ArrayList<Note>();

    @BeforeEach
    void setUp() {
        notes = List.of(
                new Note(1,"Test1","Test1",false,null),
                new Note(2,"Test2","Test2",false,null),
                new Note(4,"Test4","Test4",false,null),
                new Note(5,"Test5","Test5",false,null)
        );

        int page = 0;
        int size = 4;

        paging = PageRequest.of(page, size);

        notesPage = new PageImpl<>(notes);
    }

    @Test
    public void DisplayAllNotes() throws Exception {
        given(this.noteRepository.findByDeletedFalseOrderByCreatedtimeDesc(paging)).willReturn(notesPage);

        ResultActions result = this.mvc.perform(get("/api/notetaking/?page=0&size=4"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalItems").value(notesPage.getTotalElements()))
                .andExpect(jsonPath("$.notes.[0].id").value(notes.get(0).getId()))
                .andExpect(jsonPath("$.notes.[0].title").value(notes.get(0).getTitle()))
                .andExpect(jsonPath("$.notes.[0].content").value(notes.get(0).getContent()))
                .andExpect(jsonPath("$.notes.[1].id").value(notes.get(1).getId()))
                .andExpect(jsonPath("$.notes.[1].title").value(notes.get(1).getTitle()))
                .andExpect(jsonPath("$.notes.[1].content").value(notes.get(1).getContent()))
                .andExpect(jsonPath("$.notes.[2].id").value(notes.get(2).getId()))
                .andExpect(jsonPath("$.notes.[2].title").value(notes.get(2).getTitle()))
                .andExpect(jsonPath("$.notes.[2].content").value(notes.get(2).getContent()))
                .andExpect(jsonPath("$.notes.[3].id").value(notes.get(3).getId()))
                .andExpect(jsonPath("$.notes.[3].title").value(notes.get(3).getTitle()))
                .andExpect(jsonPath("$.notes.[3].content").value(notes.get(3).getContent()))
                .andExpect(jsonPath("$.totalPages").value(notesPage.getTotalPages()))
                .andExpect(jsonPath("$.currentPage").value(notesPage.getNumber()))
                .andDo(print());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        verify(noteRepository).findByDeletedFalseOrderByCreatedtimeDesc(pageableCaptor.capture());
    }

    @Test
    public void getNotebyId() throws Exception {
        int noteID = 2;
        boolean value = false;

        given(noteRepository.findByIdAndDeleted(noteID, value)).willReturn(notes.get(1));
        this.mvc.perform(get("/api/notetaking/{id}", noteID)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(notes.get(1).getId()))
                .andExpect(jsonPath("$.title").value(notes.get(1).getTitle()))
                .andExpect(jsonPath("$.content").value(notes.get(1).getContent()))
                .andDo(print());
    }

    @Test
    public void createNotes() throws Exception {
        Note note1 = new Note(10,"Test10","Test100",false,null);

        this.mvc.perform(post("/api/notetaking/addNote").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note1)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void UpdateNotes() throws Exception {
        int noteID = 5;

        boolean value = false;

        Note updatedNote = new Note(noteID,"Testing Editing","Editing Note 123",false,null);

        given(noteRepository.findByIdAndDeleted(noteID, value)).willReturn(notes.get(3));
        given(noteRepository.save(any(Note.class))).willReturn(updatedNote);

        this.mvc.perform(put("/api/notetaking/update/{id}", noteID).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedNote.getId()))
                .andExpect(jsonPath("$.title").value(updatedNote.getTitle()))
                .andExpect(jsonPath("$.content").value(updatedNote.getContent()))
                .andDo(print());
    }

    @Test
    void DeleteNoteById() throws Exception {
        int noteID = 4;

        Note deletedNote = new Note(4,"Test4","Test4",true,null);

        willDoNothing().given(noteRepository).deleteNote(noteID);


        this.mvc.perform(post("/api/notetaking/delete/{ids}", noteID).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deletedNote)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void DeleteAllNotes() throws Exception {
        doNothing().when(noteRepository).deleteAllNotes();


        this.mvc.perform(post("/api/notetaking/delete_all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}

