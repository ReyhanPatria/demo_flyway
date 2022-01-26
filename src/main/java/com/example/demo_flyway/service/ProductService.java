package com.example.demo_flyway.service;

import com.example.demo_flyway.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductService productService;

    public List<Product> findAll() {
        return productService.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productService.findById(id);
    }

    public Product save(Product product) {
        return productService.save(product);
    }
}