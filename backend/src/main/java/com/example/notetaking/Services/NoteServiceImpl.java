package com.example.notetaking.Services;

import com.example.notetaking.Entity.Note;
import com.example.notetaking.Repositories.NotetakingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NotetakingService {

    private final NotetakingRepository notetakingRepository;

    public NoteServiceImpl(NotetakingRepository notetakingRepository) {
        this.notetakingRepository = notetakingRepository;
    }

    @Override
    public Page<Note> findAllNotes(Pageable paging) {

        return notetakingRepository.findByDeletedFalseOrderByCreatedtimeDesc(paging);
    }

    @Override
    public Note saveNote(Note note) {
        return(notetakingRepository.save(note));
    }

    @Override
    public Note getNoteById(int id) {
        boolean value=false;
        return notetakingRepository.findByIdAndDeleted(id, value);
    }

    @Override
    public Note updateNote(Note updatedNote) {
        return notetakingRepository.save(updatedNote);
    }

    @Override
    public void deleteNoteByCustomQuery(List<Integer> ids) {
        for (int noteID : ids) {
            notetakingRepository.deleteNote(noteID);
        }
    }

    @Override
    public void deleteAllNotesByCustomQuery() {
        notetakingRepository.deleteAllNotes();
    }

}
