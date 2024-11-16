package com.example.makeupstore.services;

import com.example.makeupstore.entities.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final List<CategoryEntity> categories;

    public CategoryService() {
        categories = new ArrayList<>();
        categories.add(new CategoryEntity(UUID.randomUUID(), "Ojos", "Pestañas definidas con volumen para crear el efecto de mirada impactante"));
        categories.add(new CategoryEntity(UUID.randomUUID(),  "Rostro","Produtos ideales para todo tipo de piel" ));
        categories.add(new CategoryEntity(UUID.randomUUID(),  "Labios","Volumen intenso con beneficios de un balsamo"));

    }

    public List<CategoryEntity> createCategory(CategoryEntity newCategory) {
        newCategory.setId(UUID.randomUUID());
        categories.add(newCategory);
        return (List<CategoryEntity>) newCategory;
    }
    public List<CategoryEntity> getAllCategories() {
        return categories;
    }

    public Optional<CategoryEntity> getCategoryById(UUID id) {
        return categories.stream().filter(category -> category.getId().equals(id)).findFirst();
    }

    public Optional<CategoryEntity> updateCategory(UUID id, CategoryEntity updatedCategory) {
        Optional<CategoryEntity> existingCategory= getCategoryById(id);

        if (existingCategory.isPresent()) {
            CategoryEntity category = existingCategory.get();
            category.setCategoryName(updatedCategory.getCategoryName());
            category.setDescription(updatedCategory.getDescription());
            return Optional.of(category);
        }
        return Optional.empty();
    }

    public Optional<CategoryEntity> deleteCategory(UUID id) {
        Optional<CategoryEntity> categoryToDelete = getCategoryById(id);
        categoryToDelete.ifPresent(categories::remove);
        return categoryToDelete;
    }

}

