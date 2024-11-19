package com.example.makeupstore.services;


import com.example.makeupstore.entities.ClientEntity;
import com.example.makeupstore.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<Map<String,Object>> getAllClients() {
        Map<String,Object> response = new HashMap<>();
        List<ClientEntity> clients = clientRepository.findAll();
        response.put("Clients", clients);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getClientById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ClientEntity> clientFound = clientRepository.findById(id);
        if (clientFound.isPresent()) {
            response.put("Client", clientFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Client not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createClient(ClientEntity client) {
        Map<String, Object> response = new HashMap<>();
        client.setId(UUID.randomUUID());
        if (clientRepository.existsById(client.getId())) {
            response.put("Status", "Item already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            ClientEntity newClient = clientRepository.save(client);
            response.put("Status", "Item inserted successfully");
            response.put("Country", newClient);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateClient(UUID id, ClientEntity client) {
        Map<String, Object> response = new HashMap<>();
        Optional<ClientEntity> clientFound = clientRepository.findById(id);
        if (clientFound.isPresent()) {
            ClientEntity existingClient= clientFound.get();
            existingClient.setClientName(client.getClientName());
            existingClient.setEmail(client.getEmail());
            existingClient.setTelephone(client.getTelephone());
            clientRepository.save(existingClient);
            response.put("Status", "Client updated successfully");
            response.put("UpdatedClient", existingClient);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Client not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteClient(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ClientEntity> clientFound = clientRepository.findById(id);
        if (clientFound.isPresent()) {
            clientRepository.deleteById(id);
            response.put("Status", "Client deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("Status", "Client not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}

