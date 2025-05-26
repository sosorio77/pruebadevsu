package com.prueba.devsu.service;

import com.prueba.devsu.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto findAccountById(Long id);

    List<AccountDto> findAccountAll();

    AccountDto createAccount(AccountDto dto);

    AccountDto updateAccount(Long id, AccountDto dto);

}
