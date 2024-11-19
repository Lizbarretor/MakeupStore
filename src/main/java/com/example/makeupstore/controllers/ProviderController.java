package com.example.makeupstore.controllers;

import java.util.Map;
import java.util.UUID;

import com.example.makeupstore.entities.ProviderEntity;
import com.example.makeupstore.services.ProviderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    // Method to get ALL providers
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProviders() {
        return providerService.getAllProviders();
    }

    // Method to get one provider by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProvider(@PathVariable UUID id) {
        return providerService.getProviderById(id);
    }

    // Method to create one provider
    @PostMapping
    public ResponseEntity<?> createProvider(@RequestBody ProviderEntity provider) {
        return providerService.createProvider(provider);
    }

    // Method to update one provider
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProvider(
            @PathVariable UUID id, @RequestBody ProviderEntity provider) {
        return providerService.updateProvider(id, provider);
    }

    // Method to delete one provider
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProvider(@PathVariable UUID id) {
        return providerService.deleteProvider(id);
    }
}


