package com.example.makeupstore.controllers;
import java.util.Map;
import java.util.UUID;

import com.example.makeupstore.entities.ClientEntity;
import com.example.makeupstore.services.ClientService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/clients")
public class ClientsController {

    @Autowired
    private ClientService clientService;

    //method to get ALL clients
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        return clientService.getAllClients();
    }

    //Method to get one client by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable UUID id) {

        return clientService.getClientById(id);
    }

    //Method to create one client
    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientEntity client) {
        return clientService.createClient(client);
    }


    //Method to update one client
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateClient(
            @PathVariable UUID id, @RequestBody ClientEntity client) {
        return clientService.updateClient(id, client);
    }


    //Method to delete one product
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable UUID id) {
        return clientService.deleteClient(id);
    }
}
