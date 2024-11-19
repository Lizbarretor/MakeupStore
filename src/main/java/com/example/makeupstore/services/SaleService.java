package com.example.makeupstore.services;

import com.example.makeupstore.entities.SaleEntity;
import com.example.makeupstore.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaleService {

    @Autowired
    private SalesRepository salesRepository;

    public ResponseEntity<Map<String, Object>> getAllSales() {
        Map<String, Object> response = new HashMap<>();
        List<SaleEntity> sales = salesRepository.findAll();
        response.put("Sales", sales);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getSaleById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleEntity> saleFound = salesRepository.findById(id);
        if (saleFound.isPresent()) {
            response.put("Sale", saleFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Sale not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createSale(SaleEntity sale) {
        Map<String, Object> response = new HashMap<>();
        sale.setId(UUID.randomUUID());
        if (salesRepository.existsById(sale.getId())) {
            response.put("Status", "Sale already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            SaleEntity newSale = salesRepository.save(sale);
            response.put("Status", "Sale inserted successfully");
            response.put("Sale", newSale);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateSale(UUID id, SaleEntity sale) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleEntity> saleFound = salesRepository.findById(id);
        if (saleFound.isPresent()) {
            SaleEntity existingSale = saleFound.get();
            existingSale.setSaleDate(sale.getSaleDate());
            existingSale.setCustomerName(sale.getCustomerName());
            existingSale.setItemsSold(sale.getItemsSold());
            existingSale.setTotalAmount(sale.getTotalAmount());
            salesRepository.save(existingSale);
            response.put("Status", "Sale updated successfully");
            response.put("UpdatedSale", existingSale);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Sale not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteSale(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleEntity> saleFound = salesRepository.findById(id);
        if (saleFound.isPresent()) {
            salesRepository.deleteById(id);
            response.put("Status", "Sale deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Sale not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
