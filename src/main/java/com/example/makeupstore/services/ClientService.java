package com.example.makeupstore.services;

import com.example.makeupstore.entities.ClientEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final List<ClientEntity> clients;

    public ClientService() {
        clients = new ArrayList<>();
        clients.add(new ClientEntity(UUID.randomUUID(), "Luisa Vargas", "luis@gmail.co", 320345654));
        clients.add(new ClientEntity(UUID.randomUUID(), "Carlos Rodríguez", "carlos.rodriguez@mail.com", 310456789));
        clients.add(new ClientEntity(UUID.randomUUID(), "Ana Martínez", "ana.martinez@outlook.com", 311234567));

    }

    public List<ClientEntity> createClient(ClientEntity newClient) {
        newClient.setId(UUID.randomUUID());
        clients.add(newClient);
        return (List<ClientEntity>) newClient;
    }
    public List<ClientEntity> getAllClients() {
        return clients;
    }

    public Optional<ClientEntity> getClientById(UUID id) {
        return clients.stream().filter(client -> client.getId().equals(id)).findFirst();
    }

    public Optional<ClientEntity> updateClient(UUID id, ClientEntity updatedClient) {
        Optional<ClientEntity> existingClient= getClientById(id);

        if (existingClient.isPresent()) {
            ClientEntity client = existingClient.get();
            client.setClientName(updatedClient.getClientName());
            client.setEmail(updatedClient.getEmail());
            client.setTelephone(updatedClient.getTelephone());
            return Optional.of(client);
        }
        return Optional.empty();
    }

    public Optional<ClientEntity> deleteClient(UUID id) {
        Optional<ClientEntity> clientToDelete = getClientById(id);
        clientToDelete.ifPresent(clients::remove);
        return clientToDelete;
    }
}


