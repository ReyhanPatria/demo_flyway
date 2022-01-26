package com.example.demo_flyway.controller.rest;

import com.example.demo_flyway.model.entity.Transaction;
import com.example.demo_flyway.model.entity.TransactionDetail;
import com.example.demo_flyway.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {
    @Autowired
    public TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransaction() {
        return transactionService.findAll();
    }

    @GetMapping("/transaction/{transactionId}/details")
    public ResponseEntity<List<TransactionDetail>> getransactionDetails(@PathVariable Integer transactionId) {
        Optional<List<TransactionDetail>> transactionDetailList = transactionService.findDetailsByTransactionId(transactionId);
        return ResponseEntity.of(transactionDetailList);
    }
}