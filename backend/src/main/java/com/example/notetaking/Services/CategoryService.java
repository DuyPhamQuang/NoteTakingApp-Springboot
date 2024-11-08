package com.example.notetaking.Services;

import com.example.notetaking.DTO.CategoryDTO;
import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CategoryService {
    List<Category> findAllCategories();
    // updated it with UserDto
    Category getCategoryById(int id);
    Category addCategory(Category category);
    Category updateCategory(Category updatedCategory);
    void deleteCategory(Integer id);
    void removeNotes(List<Integer> ids);
}
