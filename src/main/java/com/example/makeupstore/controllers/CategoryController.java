package com.example.makeupstore.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.makeupstore.entities.CategoryEntity;
import com.example.makeupstore.services.CategoryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //method to get ALL products
    @GetMapping
    public List<CategoryEntity> getCategories(){
        return categoryService.getAllCategories();
    }

    //Method to get one product by ID
    @GetMapping("/{id}")
    public Optional<CategoryEntity> getCategories(@PathVariable UUID id){
        return categoryService.getCategoryById(id);
    }

    //Method to create one product
    @PostMapping
    public CategoryEntity createCategory(@RequestBody CategoryEntity category){
        return (CategoryEntity) categoryService.createCategory(category);
    }


    //Method to update one category
    @PutMapping("/{id}")
    public Optional<CategoryEntity> updateCategory(@PathVariable UUID id, @RequestBody CategoryEntity category) {
        return categoryService.updateCategory(id, category);
    }

    //Method to delete one Category
    @DeleteMapping("/{id}")
    public Optional<CategoryEntity> deleteCategory(@PathVariable UUID id) {
        return categoryService.deleteCategory(id);
    }

}
