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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

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

        note = new Note(1,"Test1","Test1",false,null);
        Ctest = new Category(3, "CTest1", null);
    }

    //@Test
    //void getAllNotes() {
    //    Note note1 = new Note(2,"Test2","Test2",false,null);
     //   Note note2 = new Note(3,"Test3","Test3",true,null);

       // given(notetakingrepo.findByDeletedFalseOrderByCreatedtimeDesc()).willReturn(List.of(note,note1));

       // List<Note> noteList = noteService.findAllNotes();

       // assertThat(noteList).isNotNull();
       // assertThat(noteList.size()).isEqualTo(2);
       // assertThat(noteList.contains(note2)).isFalse();
    //}

    @Test
    void getNotebyId() {
        int noteID = 365;
        Note note1 = new Note(noteID,"Test19","Test20",false,null);

        given(notetakingrepo.findById(noteID)).willReturn(Optional.of(note1));

        Note savedNote = noteService.getNoteById(noteID);

        assertThat(savedNote).isNotNull();
    }

    @Test
    void createNoteandCategory()
        throws JsonProcessingException {
        //Note note1 = new Note(2,"Test2","Test2",false,null);

        Set<Category> SetofCategory = new HashSet<>();
        SetofCategory.add(Ctest);
        Note note2 = new Note(2,"Test2","Test2",null, SetofCategory);


        given(notetakingrepo.save(note2)).willReturn(note2);

        Note noteTest = noteService.saveNote(note2);
        String result = new ObjectMapper().writeValueAsString(noteTest);

        assertEquals(note2,noteTest);

        Assertions.assertTrue(result.contains("Test2"));
        Assertions.assertTrue(result.contains("CTest1"));
        Assertions.assertTrue(result.contains("notes"));
    }

}
