package com.example.notetaking.Services;

import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CategoryService {
    List<Category> findAllCategories();
    Category addCategory(Category category);
}
