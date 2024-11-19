package com.example.makeupstore.repositories;

import com.example.makeupstore.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, UUID> {
}

