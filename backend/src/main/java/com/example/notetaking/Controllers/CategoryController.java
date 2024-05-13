package com.example.notetaking.Controllers;

import com.example.notetaking.Entity.Category;
import com.example.notetaking.Entity.Note;
import com.example.notetaking.Services.CategoryService;
import com.example.notetaking.Services.CategoryServiceImpl;
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

@RestController
@RequestMapping("/api/notetaking/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/")
    ResponseEntity<Map<String, Object>> getAllCategories(@RequestParam(required = false) String title) {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("Categories", categoryService.findAllCategories());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addCategory")
    ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }

}
