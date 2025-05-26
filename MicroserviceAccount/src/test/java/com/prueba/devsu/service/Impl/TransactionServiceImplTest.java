package com.prueba.devsu.service.Impl;

import com.prueba.devsu.dto.TransactionDTO;
import com.prueba.devsu.entity.AccountEntity;
import com.prueba.devsu.entity.TransactionEntity;
import com.prueba.devsu.exception.AccountNotFoundException;
import com.prueba.devsu.exception.InsufficientBalanceException;
import com.prueba.devsu.repository.AccountRepository;
import com.prueba.devsu.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findTransactionAll() {

        AccountEntity account = new AccountEntity();
        account.setId(1L);
        account.setInitialBalance(200.0);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setId(1L);
        transaction.setAmount(100.0);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionType("DEP");
        transaction.setBalance(200.0);
        transaction.setAccount(account);

        Mockito.when(transactionRepository.findAll()).thenReturn(List.of(transaction));

        List<TransactionDTO> result = transactionService.findTransactionAll();

        Assertions.assertEquals(1, result.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    public void createTransaction() {
        Long accountId = 1L;
        double depositAmount = 100.0;

        AccountEntity account = new AccountEntity();
        account.setId(accountId);
        account.setInitialBalance(200.0);

        TransactionDTO dto = TransactionDTO
                .builder()
                .accountId(accountId)
                .transactionType("DEP")
                .amount(depositAmount)
                .build();


        Mockito.when(accountRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
        Mockito.when(accountRepository.save(Mockito.any(AccountEntity.class))).thenAnswer(i -> i.getArgument(0));
        Mockito.when(transactionRepository.save(Mockito.any(TransactionEntity.class)))
                .thenAnswer(i -> {
                    TransactionEntity tx = i.getArgument(0);
                    tx.setId(10L);
                    return tx;
                });

        TransactionDTO result = transactionService.createTransaction(dto);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals(300.0, result.getBalance());
        verify(accountRepository).save(account);
        verify(transactionRepository).save(Mockito.any(TransactionEntity.class));
    }

    @Test
    public void createTransactionInsufficientBalanceException() {
        Long accountId = 1L;
        double withdrawalAmount = -500.0;

        AccountEntity account = new AccountEntity();
        account.setId(accountId);
        account.setInitialBalance(100.0);

        TransactionDTO dto = TransactionDTO
                .builder()
                .accountId(accountId)
                .transactionType("DEP")
                .amount(withdrawalAmount)
                .build();

        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        assertThrows(InsufficientBalanceException.class, () -> transactionService.createTransaction(dto));

        verify(accountRepository, never()).save(any());
        verify(transactionRepository, never()).save(any());
    }

    @Test
    public void createTransactionAccountNotFoundException() {
        Long accountId = 99L;

        TransactionDTO dto = TransactionDTO
                .builder()
                .accountId(accountId)
                .transactionType("DEP")
                .amount(100.0)
                .build();

        Mockito.when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> transactionService.createTransaction(dto));

        verify(transactionRepository, never()).save(any());
    }
}