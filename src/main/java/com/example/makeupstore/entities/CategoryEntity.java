package com.example.makeupstore.entities;

import java.util.UUID;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Setter
@Getter
@Data
@NoArgsConstructor

public class CategoryEntity {
    private UUID id;
    private String categoryName;
    private String description;

    public CategoryEntity(UUID id, String categoryName, String description) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;

    }
}
