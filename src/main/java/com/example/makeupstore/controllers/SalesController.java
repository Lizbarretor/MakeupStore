package com.example.makeupstore.controllers;

import java.util.Map;
import java.util.UUID;

import com.example.makeupstore.entities.SaleEntity;
import com.example.makeupstore.services.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/sales")
public class SalesController {

    @Autowired
    private SaleService saleService;

    // Method to get ALL sales
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSales() {
        return saleService.getAllSales();
    }

    // Method to get one sale by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSale(@PathVariable UUID id) {
        return saleService.getSaleById(id);
    }

    // Method to create one sale
    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleEntity sale) {
        return saleService.createSale(sale);
    }

    // Method to update one sale
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateSale(
            @PathVariable UUID id, @RequestBody SaleEntity sale) {
        return saleService.updateSale(id, sale);
    }

    // Method to delete one sale
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteSale(@PathVariable UUID id) {
        return saleService.deleteSale(id);
    }
}

