package com.example.demo_flyway.service;

import com.example.demo_flyway.model.entity.Transaction;
import com.example.demo_flyway.model.entity.TransactionDetail;
import com.example.demo_flyway.repository.TransactionDetailRepository;
import com.example.demo_flyway.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findAllByUserId(Integer userId) {
        return transactionRepository.findAllByUserId(userId);
    }

    public Optional<List<TransactionDetail>> findDetailsByTransactionId(Integer transactionId) {
        List<TransactionDetail> transactionDetailList = transactionDetailRepository.findAllByTransactionId(transactionId);
        if(transactionDetailList.isEmpty())
            return Optional.empty();

        return Optional.of(transactionDetailList);
    }
}