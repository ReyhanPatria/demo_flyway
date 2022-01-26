package com.example.demo_flyway.controller.rest;

import com.example.demo_flyway.model.entity.Product;
import com.example.demo_flyway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        product = productService.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        return ResponseEntity.of(product);
    }
}