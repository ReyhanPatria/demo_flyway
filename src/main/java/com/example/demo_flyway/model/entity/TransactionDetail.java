package com.example.demo_flyway.model.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "transaction_details")
@Data
public class TransactionDetail {
    @EmbeddedId
    private TransactionDetailId id;
    private Integer quantity;
}