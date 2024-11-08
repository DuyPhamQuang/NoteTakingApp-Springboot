package com.example.notetaking.Controllers;

import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import com.example.notetaking.Repositories.NotetakingRepository;
import com.example.notetaking.Services.NoteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotetakingServiceTest {
    @Mock
    private NotetakingRepository notetakingrepo;

    @InjectMocks
    private NoteServiceImpl noteService;

    private Note note;
    private Category Ctest;

    @BeforeEach
    public void setup(){
        //Ctest = new Category(3, "CTest1", null, null);
        //Ctest2 = new Category(3, "CTest1", null, null);
        note = new Note(1,"Test1","Test1",false, null);
        Note note2 = new Note(3,"Test3","Test3",false,null);
        Note note3 = new Note(4,"Test4","Test4",false,null);
    }

    /*@Test
    void getAllNotes() {
        Note note1 = new Note(2,"Test2","Test2",false, null);
        Note note2 = new Note(3,"Test3","Test3",false,null);
        Note note3 = new Note(4,"Test4","Test4",false,null);
        Note note4 = new Note(5,"Test5","Test5",false,null);
        Note note5 = new Note(6,"Test6","Test6",false,null);
        Note note6 = new Note(7,"Test7","Test7",false,null);
        Note note7 = new Note(8,"Test8","Test9",false,null);
        Note note8 = new Note(9,"Test9","Test8",false,null);
        Note note9 = new Note(10,"Test10","Test10",false,null);

        int page = 0;
        int size = 4;

        Pageable paging = PageRequest.of(page, size);

        given(notetakingrepo.findByDeletedFalseOrderByCreatedtimeDesc(paging)).willReturn(Page.);

        Page<Note> noteList = noteService.findAllNotes(paging);

        assertThat(noteList).isNotNull();
        assertThat(noteList.getNumber()).isEqualTo(0);
        assertThat(noteList.getTotalElements()).isEqualTo(4);
        assertThat(noteList.getTotalPages()).isEqualTo(3);
        //assertThat(noteList.getContent(notelist)).isFalse();
    } */

    @Test
    public void getNotebyId() {
        int noteID = 365;
        boolean value = false;
        Note note1 = new Note(noteID,"Test19","Test20",false,null);

        given(notetakingrepo.findByIdAndDeleted(noteID, value)).willReturn(note1);

        Note savedNote = noteService.getNoteById(noteID);

        assertThat(savedNote).isNotNull();
    }

    @Test
    public void createNote() throws JsonProcessingException {

        Set<Category> SetofCategory = new HashSet<>();
        SetofCategory.add(Ctest);
        Note note2 = new Note(2,"Test2","Test2",false, SetofCategory);


        given(notetakingrepo.save(note2)).willReturn(note2);

        Note noteTest = noteService.saveNote(note2);

        assertEquals(note2,noteTest);
    }

    @Test
    public void updateNote(){
        int noteID = 1;
        boolean value = false;

        Set<Category> SetofCategory = new HashSet<>();
        SetofCategory.add(Ctest);

        Note newNote = new Note(noteID,"Testing Editing","Editing Note 123",false, SetofCategory);

        given(notetakingrepo.save(any(Note.class))).willReturn(newNote);

        Note updatedNote2 = noteService.updateNote(newNote);

        assertEquals(updatedNote2, newNote);
    }

    @Test
    public void DeleteNoteById(){
        // given - precondition or setup
        List<Integer> noteID = List.of(1,3,4);

        for (int id : noteID) {
            willDoNothing().given(notetakingrepo).deleteNote(id);
        }


        noteService.deleteNoteByCustomQuery(noteID);

        for (int id: noteID) {
            verify(notetakingrepo, times(1)).deleteNote(id);
        }
    }

    @Test
    public void DeleteAll(){
        willDoNothing().given(notetakingrepo).deleteAllNotes();

        noteService.deleteAllNotesByCustomQuery();

        verify(notetakingrepo, times(1)).deleteAllNotes();
    }

}
