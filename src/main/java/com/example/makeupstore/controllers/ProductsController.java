package com.example.makeupstore.controllers;

import java.util.Map;
import java.util.UUID;

import com.example.makeupstore.entities.ProductEntity;
import com.example.makeupstore.services.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v2/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    //method to get ALL products
    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllProducts(){
        return productService.getAllProducts();
    }

    //Method to get one product by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable UUID id){

        return productService.getProductById(id);
    }

    //Method to create one product
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductEntity product){
        return productService.createProduct(product);
    }


    //Method to update one product
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateProduct(
            @PathVariable UUID id, @RequestBody ProductEntity product){
        return productService.updateProduct(id,product);
    }


    //Method to delete one product
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deleteProduct(@PathVariable UUID id){
        return productService.deleteProduct(id);
    }



}
