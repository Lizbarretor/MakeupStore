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

public class ClientEntity {
    @Id
    private UUID id;
    private String clientName;
    private String email;
    private int telephone;

    @PrePersist
    public void generateUUID(){
        if(id == null){
            id = UUID.randomUUID();
        };

    }
}



