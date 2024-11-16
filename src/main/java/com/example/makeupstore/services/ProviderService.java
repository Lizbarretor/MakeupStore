package com.example.makeupstore.services;

import com.example.makeupstore.entities.ProviderEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProviderService {
    private final List<ProviderEntity> providers;

    public ProviderService() {
        providers = new ArrayList<>();
        providers.add(new ProviderEntity(UUID.randomUUID(), "Camilo Vargas", "Pestañiña", 300345654));
        providers.add(new ProviderEntity(UUID.randomUUID(), "Paola Rodríguez", "Labiales", 322456789));
        providers.add(new ProviderEntity(UUID.randomUUID(), "Jazmin Cardenas", "Sombras", 319234567));

    }

    public List<ProviderEntity> createProvider(ProviderEntity newProvider) {
        newProvider.setId(UUID.randomUUID());
        providers.add(newProvider);
        return (List<ProviderEntity>) newProvider;
    }
    public List<ProviderEntity> getAllProviders() {
        return providers;
    }

    public Optional<ProviderEntity> getProviderById(UUID id) {
        return providers.stream().filter(provide-> provide.getId().equals(id)).findFirst();
    }

    public Optional<ProviderEntity> updateProvider(UUID id, ProviderEntity updatedProvider) {
        Optional<ProviderEntity> existingProvider= getProviderById(id);

        if (existingProvider.isPresent()) {
            ProviderEntity provider = existingProvider.get();
            provider.setProviderName(updatedProvider.getProviderName());
            provider.setProductsProvided(updatedProvider.getProductsProvided());
            provider.setTelephone(updatedProvider.getTelephone());
            return Optional.of(provider);
        }
        return Optional.empty();
    }

    public Optional<ProviderEntity> deleteProvider(UUID id) {
        Optional<ProviderEntity> providerToDelete = getProviderById(id);
        providerToDelete.ifPresent(providers::remove);
        return providerToDelete;
    }

}



