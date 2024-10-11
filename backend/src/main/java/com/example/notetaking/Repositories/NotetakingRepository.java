package com.example.notetaking.Repositories;

import com.example.notetaking.Entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("NotetakingRepository")
public interface NotetakingRepository extends SearchRepository<Note, Integer> {
    Page<Note> findByDeletedFalseOrderByCreatedtimeDesc(Pageable paging);

    Note findByIdAndDeleted(int id, boolean value);

    @Modifying
    @Transactional
    @Query(value = "update notetaking_note set deleted = true where id = :id", nativeQuery = true)
    void deleteNote(@Param(value = "id") int id);

    @Modifying
    @Transactional
    @Query(value = "update notetaking_note set deleted = true", nativeQuery = true)
    void deleteAllNotes();
}
