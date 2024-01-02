package com.example.notetaking.Services;

import com.example.notetaking.Entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface NotetakingService {
    Page<Note> findAllNotes(Pageable paging);

    Note saveNote(Note note);
    Note getNoteById(int id);
    Note updateNote(Note updatedNote);
    void deleteNoteByCustomQuery(List<Integer> ids);
    void deleteAllNotesByCustomQuery();
}
