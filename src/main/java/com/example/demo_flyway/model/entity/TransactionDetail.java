package com.example.demo_flyway.model.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "transaction_details")
@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TransactionDetail {
    @EmbeddedId
    private TransactionDetailId id;
    private Integer quantity;
}