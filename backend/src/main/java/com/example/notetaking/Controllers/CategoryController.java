package com.example.notetaking.Controllers;

import com.example.notetaking.Services.CategoryService;
import com.example.notetaking.Services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notetaking/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;
}
