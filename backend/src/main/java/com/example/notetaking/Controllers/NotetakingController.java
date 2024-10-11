package com.example.notetaking.Controllers;

import com.example.notetaking.DTO.PageDTO;
import com.example.notetaking.DTO.PageableSearchRequestDTO;
import com.example.notetaking.DTO.SearchRequestDTO;
import com.example.notetaking.Entity.Note;
import com.example.notetaking.NotetakingApplication;
import com.example.notetaking.Services.NoteServiceImpl;
import com.example.notetaking.Services.NotetakingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.DefaultFlowMessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/notetaking")
@CrossOrigin
@Slf4j
public class NotetakingController {
    @Autowired
    private NoteServiceImpl noteService;

    @GetMapping("/")
    ResponseEntity<Map<String, Object>> getAllNotes(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "18") int size) {
        try {
            List<Note> notes = new ArrayList<Note>();
            Pageable paging = PageRequest.of(page, size);

            Page<Note> pageNotes = noteService.findAllNotes(paging);
            notes = pageNotes.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put("notes", notes);
            response.put("currentPage", pageNotes.getNumber());
            response.put("totalItems", pageNotes.getTotalElements());
            response.put("totalPages", pageNotes.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteById(@PathVariable("id") int noteId){
        try {
            Note note = noteService.getNoteById(noteId);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/addNote")
    ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.saveNote(note), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") int noteId,
                                           @RequestBody Note note){
        try {
            Note savedNote = noteService.getNoteById(noteId);
            savedNote.setTitle(note.getTitle());
            savedNote.setContent(note.getContent());
            savedNote.setCategories(note.getCategories());
            Note updatedNote = noteService.updateNote(savedNote);
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/delete/{ids}")
    public ResponseEntity<String> deleteNote(@PathVariable() List<Integer> ids) {
        noteService.deleteNoteByCustomQuery(ids);
        return new ResponseEntity<String>("Notes deleted successfully!.", HttpStatus.OK);
    }

    @PutMapping("/delete_all")
    public ResponseEntity<String> deleteAllNote() {
        noteService.deleteAllNotesByCustomQuery();
        return new ResponseEntity<String>("All Notes deleted successfully!.", HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchNotes(PageableSearchRequestDTO pageableSearchRequestDTO) {
        try {
            PageDTO<Note> searchedNotes = noteService.searchNotes(pageableSearchRequestDTO.getText(),
                                                                  pageableSearchRequestDTO.getFields(),
                                                                  pageableSearchRequestDTO.getLimit(),
                                                                  pageableSearchRequestDTO.getPageOffset());


            return new ResponseEntity<>(searchedNotes, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
