package com.example.notetaking.Services;

import com.example.notetaking.DTO.PageDTO;
import com.example.notetaking.Entity.Note;
import com.example.notetaking.Repositories.NotetakingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
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

    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("title","content");
    @Override
    public PageDTO<Note> searchNotes(String text, List<String> fields, int limit, int pageOffset) {
        List<String> fieldsToSearchBy = getFieldsToSearchBy(fields);

        return notetakingRepository.searchPageBy(
                text, limit, pageOffset*limit, fieldsToSearchBy.toArray(new String[0]));
    }

    private List<String> getFieldsToSearchBy(List<String> fields) {
        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream().anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if (containsInvalidField) {
            throw new IllegalArgumentException();
        }
        return fieldsToSearchBy;
    }
}
