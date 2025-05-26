package com.prueba.devsu.service.Impl;

import com.prueba.devsu.dto.AccountDto;
import com.prueba.devsu.entity.AccountEntity;
import com.prueba.devsu.exception.AccountNotFoundException;
import com.prueba.devsu.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class AccountServiceImplTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImpl service;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAccountById() {
        AccountEntity entity = new AccountEntity();
        entity.setId(1L);
        entity.setAccountNumber("123");
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));

        AccountDto result = service.findAccountById(1L);

        Assertions.assertEquals("123", result.getAccountNumber());
        verify(repository).findById(1L);
    }

    @Test
    public void findAccountByIdNotFound() {
        Long accountId = 2L;
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> service.findAccountById(accountId));
    }

    @Test
    public void findAccountAll() {

        AccountEntity entity1 = new AccountEntity();
        entity1.setId(1L);
        entity1.setAccountNumber("123");
        AccountEntity entity2 = new AccountEntity();
        entity2.setId(2L);
        entity2.setAccountNumber("456");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        List<AccountDto> result = service.findAccountAll();

        Assertions.assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    public void createAccount() {

        AccountDto dto = AccountDto
                .builder()
                .accountNumber("789")
                .build();

        AccountEntity savedEntity = new AccountEntity();
        savedEntity.setAccountNumber("789");

        Mockito.when(repository.save(Mockito.any(AccountEntity.class))).thenReturn(savedEntity);

        AccountDto result = service.createAccount(dto);

        Assertions.assertEquals("789", result.getAccountNumber());
        verify(repository).save(Mockito.any(AccountEntity.class));

    }

    @Test
    public void updateAccount() {
        AccountDto dto = AccountDto
                .builder()
                .accountType("Corriente")
                .status(true)
                .initialBalance(200.0)
                .build();

        AccountEntity existing = new AccountEntity();
        existing.setId(1L);

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(existing));
        Mockito.when(repository.save(Mockito.any(AccountEntity.class))).thenReturn(existing);

        AccountDto result = service.updateAccount(1L, dto);

        Assertions.assertEquals("Corriente", result.getAccountType());
        verify(repository).findById(1L);
        verify(repository).save(existing);
    }

    @Test
    public void updateAccountNotFound() {

        AccountDto dto = AccountDto.builder().build();

        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> service.updateAccount(1L, dto));
    }
}