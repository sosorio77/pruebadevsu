package com.prueba.devsu.mapper;

import com.prueba.devsu.dto.TransactionDTO;
import com.prueba.devsu.entity.TransactionEntity;

public class TransaccionMapper {

    public static TransactionDTO TransactionEntityToTransactionDTO(TransactionEntity entity) {
        return TransactionDTO.builder()
                .id(entity.getId())
                .transactionDate(entity.getTransactionDate().toString())
                .transactionType(entity.getTransactionType())
                .amount(entity.getAmount())
                .balance(entity.getBalance())
                .accountId(entity.getAccount().getId())
                .build();
    }

}
