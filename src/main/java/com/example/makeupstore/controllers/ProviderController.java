package com.example.makeupstore.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.makeupstore.entities.ProviderEntity;
import com.example.makeupstore.services.ProviderService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/Providers")
public class ProviderController {

    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    //method to get ALL products
    @GetMapping
    public List<ProviderEntity> getProviders(){
        return providerService.getAllProviders();
    }

    //Method to get one product by ID
    @GetMapping("/{id}")
    public Optional<ProviderEntity> getProviders(@PathVariable UUID id){
        return providerService.getProviderById(id);
    }

    //Method to create one product
    @PostMapping
    public ProviderEntity createProvider(@RequestBody ProviderEntity provider){
        return (ProviderEntity) providerService.createProvider(provider);
    }


    //Method to update one product
    @PutMapping("/{id}")
    public Optional<ProviderEntity> updateProvider(@PathVariable UUID id, @RequestBody ProviderEntity provider) {
        return providerService.updateProvider(id, provider);
    }

    //Method to delete one product
    @DeleteMapping("/{id}")
    public Optional<ProviderEntity> deleteProvider(@PathVariable UUID id) {
        return providerService.deleteProvider(id);
    }

}

