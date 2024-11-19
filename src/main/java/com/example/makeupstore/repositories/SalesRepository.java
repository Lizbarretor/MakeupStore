package com.example.makeupstore.repositories;

import com.example.makeupstore.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalesRepository extends JpaRepository<SaleEntity, UUID> {
}

