package com.prueba.devsu.service.Impl;

import com.prueba.devsu.constant.GenericMessages;
import com.prueba.devsu.dto.TransactionDTO;
import com.prueba.devsu.entity.AccountEntity;
import com.prueba.devsu.entity.TransactionEntity;
import com.prueba.devsu.exception.AccountNotFoundException;
import com.prueba.devsu.exception.InsufficientBalanceException;
import com.prueba.devsu.mapper.TransaccionMapper;
import com.prueba.devsu.repository.AccountRepository;
import com.prueba.devsu.repository.TransactionRepository;
import com.prueba.devsu.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<TransactionDTO> findTransactionAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransaccionMapper::TransactionEntityToTransactionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO dto) {
        Long accountId = dto.getAccountId();
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(String.format(GenericMessages.ACCOUNT_NOT_FOUND, accountId)));

        double availableBalance = account.getInitialBalance();
        double newBalance = availableBalance + dto.getAmount();

        if (newBalance < 0) {
            throw new InsufficientBalanceException(GenericMessages.BALANCE_NOT_AVAILABLE);
        }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setAmount(dto.getAmount());
        transaction.setBalance(newBalance);
        transaction.setAccount(account);

        return TransaccionMapper.TransactionEntityToTransactionDTO(transactionRepository.save(transaction));
    }


}
