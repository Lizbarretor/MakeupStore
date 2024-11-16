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

public class ClientEntity {
    private UUID id;
    private String clientName;
    private String email;
    private int telephone;

    public ClientEntity(UUID id, String clientName, String email, int telephone) {
        this.id = id;
        this.clientName = clientName;
        this.email = email;
        this.telephone = telephone;

    }
}



