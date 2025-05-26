package com.prueba.devsu.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransactionDTO {
    private Long id;
    private String transactionDate;
    private String transactionType;
    private Double amount;
    private Double balance;
    private Long accountId;
}
