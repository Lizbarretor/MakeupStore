package com.example.makeupstore.services;
import com.example.makeupstore.entities.CategoryEntity;
import com.example.makeupstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<Map<String, Object>> getAllCategories() {
        Map<String, Object> response = new HashMap<>();
        List<CategoryEntity> categories = categoryRepository.findAll();
        response.put("Categories", categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getCategoryById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoryEntity> categoryFound = categoryRepository.findById(id);
        if (categoryFound.isPresent()) {
            response.put("Category", categoryFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Category not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createCategory(CategoryEntity category) {
        Map<String, Object> response = new HashMap<>();
        category.setId(UUID.randomUUID());
        if (categoryRepository.existsById(category.getId())) {
            response.put("Status", "Category already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            CategoryEntity newCategory = categoryRepository.save(category);
            response.put("Status", "Category inserted successfully");
            response.put("Category", newCategory);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateCategory(UUID id, CategoryEntity category) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoryEntity> categoryFound = categoryRepository.findById(id);
        if (categoryFound.isPresent()) {
            CategoryEntity existingCategory = categoryFound.get();
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setDescription(category.getDescription());
            categoryRepository.save(existingCategory);
            response.put("Status", "Category updated successfully");
            response.put("UpdatedCategory", existingCategory);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Category not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteCategory(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoryEntity> categoryFound = categoryRepository.findById(id);
        if (categoryFound.isPresent()) {
            categoryRepository.deleteById(id);
            response.put("Status", "Category deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Category not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}

