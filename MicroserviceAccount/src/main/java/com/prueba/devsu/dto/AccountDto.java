package com.prueba.devsu.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountDto {


    private Long id;

    private String accountNumber;

    private String accountType;

    private Double initialBalance;

    private Boolean status;

}
