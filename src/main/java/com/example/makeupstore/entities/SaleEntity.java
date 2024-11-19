package com.example.makeupstore.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
public class SaleEntity {
    @Id
    private UUID id;
    private String saleDate;
    private String customerName;
    private double totalAmount;
    private int itemsSold;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
