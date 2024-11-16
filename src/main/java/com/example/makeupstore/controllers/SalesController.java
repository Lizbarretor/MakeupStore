package com.example.makeupstore.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.makeupstore.entities.SaleEntity;
import com.example.makeupstore.services.SaleService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/sales")
public class SalesController {

    private final SaleService saleService;

    @Autowired
    public SalesController(SaleService saleService) {
        this.saleService = saleService;
    }

    //method to get ALL products
    @GetMapping
    public List<SaleEntity> getSales(){
        return saleService.getAllSales();
    }

    //Method to get one product by ID
    @GetMapping("/{id}")
    public Optional<SaleEntity> getSales(@PathVariable UUID id){
        return saleService.getSaleById(id);
    }

    //Method to create one product
    @PostMapping
    public SaleEntity createSale(@RequestBody SaleEntity sale){
        return (SaleEntity) saleService.createSale(sale);
    }


    //Method to update one product
    @PutMapping("/{id}")
    public Optional<SaleEntity> updateSale(@PathVariable UUID id, @RequestBody SaleEntity sale) {
        return saleService.updateSale(id, sale);
    }

    //Method to delete one product
    @DeleteMapping("/{id}")
    public Optional<SaleEntity> deleteSale(@PathVariable UUID id) {
        return saleService.deleteSale(id);
    }

}

