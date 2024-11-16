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

public class ProviderEntity {
    private UUID id;
    private String providerName;
    private String productsProvided;
    private int telephone;

    public ProviderEntity(UUID id, String providerName, String productsProvided, int telephone) {
        this.id = id;
        this.providerName = providerName;
        this.productsProvided = productsProvided;
        this.telephone = telephone;

    }
}

