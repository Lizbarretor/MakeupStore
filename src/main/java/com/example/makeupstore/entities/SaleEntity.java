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

public class SaleEntity {
    private UUID id;
    private int idClient;
    private String payment;
    private double total;

    public SaleEntity(UUID id,int idClient, String payment, double total) {
        this.id = id;
        this.idClient = idClient;
        this.payment = payment;
        this.total = total;

    }
}
