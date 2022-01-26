package com.example.demo_flyway.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class TransactionDetailId {
    private Integer transactionId;
    private Integer productId;
}