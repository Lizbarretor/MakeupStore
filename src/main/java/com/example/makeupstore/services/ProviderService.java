package com.example.makeupstore.services;

import com.example.makeupstore.entities.ProviderEntity;
import com.example.makeupstore.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public ResponseEntity<Map<String, Object>> getAllProviders() {
        Map<String, Object> response = new HashMap<>();
        List<ProviderEntity> providers = providerRepository.findAll();
        response.put("Providers", providers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getProviderById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProviderEntity> providerFound = providerRepository.findById(id);
        if (providerFound.isPresent()) {
            response.put("Provider", providerFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Provider not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createProvider(ProviderEntity provider) {
        Map<String, Object> response = new HashMap<>();
        provider.setId(UUID.randomUUID());
        if (providerRepository.existsById(provider.getId())) {
            response.put("Status", "Provider already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            ProviderEntity newProvider = providerRepository.save(provider);
            response.put("Status", "Provider inserted successfully");
            response.put("Provider", newProvider);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateProvider(UUID id, ProviderEntity provider) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProviderEntity> providerFound = providerRepository.findById(id);
        if (providerFound.isPresent()) {
            ProviderEntity existingProvider = providerFound.get();
            existingProvider.setProviderName(provider.getProviderName());
            existingProvider.setProductsProvided(provider.getProductsProvided());
            existingProvider.setTelephone(provider.getTelephone());
            providerRepository.save(existingProvider);
            response.put("Status", "Provider updated successfully");
            response.put("UpdatedProvider", existingProvider);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Provider not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteProvider(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProviderEntity> providerFound = providerRepository.findById(id);
        if (providerFound.isPresent()) {
            providerRepository.deleteById(id);
            response.put("Status", "Provider deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Provider not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
