package com.example.makeupstore.services;


import com.example.makeupstore.entities.ProductEntity;
import com.example.makeupstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Map<String,Object>> getAllProducts() {
        Map<String,Object> response = new HashMap<>();
        List<ProductEntity> products = productRepository.findAll();
        response.put("Products", products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getProductById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProductEntity> productFound = productRepository.findById(id);
        if (productFound.isPresent()) {
            response.put("Product", productFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Error", "Product not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createProduct(ProductEntity product) {
        Map<String, Object> response = new HashMap<>();
        product.setId(UUID.randomUUID());
        if (productRepository.existsById(product.getId())) {
            response.put("Status", "Item already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            ProductEntity newProduct = productRepository.save(product);
            response.put("Status", "Item inserted successfully");
            response.put("Country", newProduct);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, Object>> updateProduct(UUID id, ProductEntity product) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProductEntity> productFound = productRepository.findById(id);
        if (productFound.isPresent()) {
            ProductEntity existingProduct = productFound.get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            productRepository.save(existingProduct);
            response.put("Status", "Product updated successfully");
            response.put("UpdatedProduct", existingProduct);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("Status", "Product not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> deleteProduct(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProductEntity> productFound = productRepository.findById(id);
        if (productFound.isPresent()) {
            productRepository.deleteById(id);
            response.put("Status", "Product deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("Status", "Product not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}

