package com.example.notetaking.Repositories;

import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository("CategoryRepository")
public interface CategoryRepository extends SearchRepository<Category, Integer> {
    Category findByIdAndDeleted(int id, boolean value);

    @Modifying
    @Transactional
    @Query(value = "update notetaking_category set deleted = true where id = :id", nativeQuery = true)
    void deleteCategory(@Param(value = "id") int id);

    List<Category> findByDeletedFalseOrderByIdAsc();

    @Modifying
    @Transactional
    @Query(value = "delete from notetaking_categorynote where category_id = :categoryid and note_id = :noteid", nativeQuery = true)
    void removeNote(@Param(value = "categoryid") int id1, @Param(value = "noteid") int id2);
}
