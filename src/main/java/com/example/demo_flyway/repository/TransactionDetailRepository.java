package com.example.demo_flyway.repository;

import com.example.demo_flyway.model.entity.TransactionDetail;
import com.example.demo_flyway.model.entity.TransactionDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, TransactionDetailId> {
    @Query("SELECT * FROM transaction_details trd WHERE trd.transactionDetailId.transactionId = ?1")
    public List<TransactionDetail> findAllByTransactionId(Integer transactionId);
}