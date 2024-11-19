package com.example.makeupstore.controllers;

import java.util.Map;
import java.util.UUID;

import com.example.makeupstore.entities.CategoryEntity;
import com.example.makeupstore.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Method to get ALL categories
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Method to get one category by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable UUID id) {
        return categoryService.getCategoryById(id);
    }

    // Method to create one category
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryEntity category) {
        return categoryService.createCategory(category);
    }

    // Method to update one category
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCategory(
            @PathVariable UUID id, @RequestBody CategoryEntity category) {
        return categoryService.updateCategory(id, category);
    }

    // Method to delete one category
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable UUID id) {
        return categoryService.deleteCategory(id);
    }
}
