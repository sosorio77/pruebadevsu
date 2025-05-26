package com.prueba.devsu.service.Impl;

import com.prueba.devsu.constant.GenericMessages;
import com.prueba.devsu.dto.AccountDto;
import com.prueba.devsu.entity.AccountEntity;
import com.prueba.devsu.exception.AccountNotFoundException;
import com.prueba.devsu.mapper.AccountMapper;
import com.prueba.devsu.repository.AccountRepository;
import com.prueba.devsu.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountDto findAccountById(Long id) {
        return repository.findById(id)
                .map(AccountMapper::AccountEntityToAccountDto)
                .orElseThrow(() -> new AccountNotFoundException(String.format(GenericMessages.ACCOUNT_NOT_FOUND,id)));
    }

    @Override
    public List<AccountDto> findAccountAll() {
        return repository.findAll()
                .stream()
                .map(AccountMapper::AccountEntityToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto createAccount(AccountDto dto) {
        AccountEntity entity = AccountMapper.AccountDtoToAccountEntity(dto);
        return AccountMapper.AccountEntityToAccountDto(repository.save(entity));
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto dto) {
        AccountEntity existing = repository.findById(id).orElseThrow(() -> new AccountNotFoundException(String.format(GenericMessages.ACCOUNT_NOT_FOUND,id)));
        existing.setAccountType(dto.getAccountType());
        existing.setStatus(dto.getStatus());
        existing.setInitialBalance(dto.getInitialBalance());
        return AccountMapper.AccountEntityToAccountDto(repository.save(existing));
    }



}
