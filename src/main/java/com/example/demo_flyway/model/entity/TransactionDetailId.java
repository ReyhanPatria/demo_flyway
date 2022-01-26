package com.example.demo_flyway.model.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@Data
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class TransactionDetailId implements Serializable {
    private Integer transactionId;
    private Integer productId;
}