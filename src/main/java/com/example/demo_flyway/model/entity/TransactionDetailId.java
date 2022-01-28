package com.example.demo_flyway.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@Data
public class TransactionDetailId implements Serializable {
    private Integer transactionId;
    private Integer productId;
}