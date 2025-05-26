package com.prueba.devsu.mapper;

import com.prueba.devsu.dto.AccountDto;
import com.prueba.devsu.entity.AccountEntity;

public class AccountMapper {

    public static AccountDto AccountEntityToAccountDto(AccountEntity entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .accountType(entity.getAccountType())
                .initialBalance(entity.getInitialBalance())
                .status(entity.getStatus())
                .build();
    }

    public static AccountEntity AccountDtoToAccountEntity(AccountDto dto) {
        AccountEntity entity = new AccountEntity();
        entity.setAccountNumber(dto.getAccountNumber());
        entity.setAccountType(dto.getAccountType());
        entity.setInitialBalance(dto.getInitialBalance());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}