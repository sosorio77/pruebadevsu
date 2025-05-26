package com.prueba.devsu.service.Impl;

import com.prueba.devsu.dto.ClientDto;
import com.prueba.devsu.dto.ReportDTO;
import com.prueba.devsu.feignClient.ClientFeignClient;
import com.prueba.devsu.repository.AccountRepository;
import com.prueba.devsu.repository.TransactionRepository;
import com.prueba.devsu.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ClientFeignClient clientFeignClient;

    public ReportServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, ClientFeignClient clientFeignClient) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.clientFeignClient = clientFeignClient;
    }

    @Override
    public List<ReportDTO> getReport(Long clientId, LocalDate start, LocalDate end) {

        ClientDto cliente = clientFeignClient.findClientById(clientId);

        return accountRepository.findAll().stream()
                .flatMap(account -> transactionRepository
                        .findByAccountIdAndTransactionDateBetween(account.getId(), start, end)
                        .stream()
                        .map(tx -> ReportDTO.builder()
                                .fecha(tx.getTransactionDate().toString())
                                .cliente(cliente.getName())
                                .numeroCuenta(account.getAccountNumber())
                                .tipo(account.getAccountType())
                                .saldoInicial(account.getInitialBalance())
                                .estado(account.getStatus())
                                .movimiento(tx.getAmount())
                                .saldoDisponible(tx.getBalance())
                                .build()))
                .collect(Collectors.toList());
    }
}
