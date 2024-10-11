package com.example.notetaking.Controllers;

import com.example.notetaking.DTO.CategoryDTO;
import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import com.example.notetaking.Services.CategoryService;
import com.example.notetaking.Services.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notetaking/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private ModelMapper modelMapper;

    private Object convertToDto(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @GetMapping("/")
    ResponseEntity<Map<String, Object>> getAllCategories(@RequestParam(required = false) String title) {
        try {
            Map<String, Object> response = new HashMap<>();
            List<Category> categories = categoryService.findAllCategories();
            response.put("Categories", categories.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList()));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTitleCategoryById(@PathVariable("id") int categoryId){
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int categoryId,
                                                   @RequestBody Category category) {
        try{
            Category savedCategory = categoryService.getCategoryById(categoryId);
            savedCategory.setTitle(category.getTitle());
            Category updatedCategory = categoryService.updateCategory(savedCategory);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable() Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<String>("Categories deleted successfully!.", HttpStatus.OK);
    }

    @PutMapping("/remove_notes/{ids}")
    public ResponseEntity<String> removeNotesFromCategory(@PathVariable() List<Integer> ids) {
        categoryService.removeNotes(ids);
        return new ResponseEntity<String>("Notes removed successfully!.", HttpStatus.OK);
    }

}
