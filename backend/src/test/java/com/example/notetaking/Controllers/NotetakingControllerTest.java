package com.example.notetaking.Controllers;

import com.example.notetaking.Entity.Note;
import com.example.notetaking.Services.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(NotetakingController.class)
@AutoConfigureMockMvc
public class NotetakingControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private NoteServiceImpl noteservice;

    List<Note> notes = new ArrayList<Note>();

    @BeforeEach
    void setUp() {
        notes = List.of(
                new Note(1,"Test1","Test1",false,null),
                new Note(2,"Test2","Test2",false,null)
        );
    }

    //@Test
    //public void shouldDisplayAllNotes() throws Exception {
    //    given(this.noteservice.findAllNotes())
    //            .willReturn(notes);

    //    this.mvc.perform(get("/api/notetaking/"))
    //            .andExpect(status().isOk())
    //            .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    //}

    @Test
    public void createNotes() throws Exception {
        //Note note1 = new Note(15,"Test19","Test20",false,null);
        //given(this.noteservice.saveObject(note1))
          //      .willReturn(note1);

        this.mvc.perform(post("/api/notetaking/addNote"))
                .andExpect(status().isCreated());
    }

    @Test
    public void getNotebyId() throws Exception {
        int noteID = 365;
        Note note1 = new Note(noteID,"Test19","Test20",false,null);

        given(noteservice.getNoteById(noteID)).willReturn(note1);


        this.mvc.perform(get("/api/notetaking/{id}", noteID))
                .andExpect(status().isOk());
    }
}

