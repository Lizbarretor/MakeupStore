package com.example.makeupstore.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.makeupstore.entities.ClientEntity;
import com.example.makeupstore.services.ClientService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/clients")
public class ClientsController {

    private final ClientService clientService;

    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    //method to get ALL products
    @GetMapping
    public List<ClientEntity> getClients(){
        return clientService.getAllClients();
    }

    //Method to get one product by ID
    @GetMapping("/{id}")
    public Optional<ClientEntity> getClients(@PathVariable UUID id){
        return clientService.getClientById(id);
    }

    //Method to create one product
    @PostMapping
    public ClientEntity createClient(@RequestBody ClientEntity client){
        return (ClientEntity) clientService.createClient(client);
    }


    //Method to update one product
    @PutMapping("/{id}")
    public Optional<ClientEntity> updateClient(@PathVariable UUID id, @RequestBody ClientEntity client) {
        return clientService.updateClient(id, client);
    }

    //Method to delete one product
    @DeleteMapping("/{id}")
    public Optional<ClientEntity> deleteClient(@PathVariable UUID id) {
        return clientService.deleteClient(id);
    }

}
