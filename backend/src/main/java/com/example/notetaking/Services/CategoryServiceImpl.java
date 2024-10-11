package com.example.notetaking.Services;

import com.example.notetaking.DTO.CategoryDTO;
import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import com.example.notetaking.Repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findByDeletedFalseOrderByIdAsc();
    }

    @Override
    public Category getCategoryById(int id) {
        boolean value=false;
        return categoryRepository.findByIdAndDeleted(id, value);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category updatedCategory) {
        return categoryRepository.save(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteCategory(id);
    }

    @Override
    public void removeNotes(List<Integer> ids) {
        int categoryID = ids.get(0);
        for (int noteid=1; noteid < ids.size(); noteid++) {
            categoryRepository.removeNote(categoryID, ids.get(noteid));
        }
    }
}
