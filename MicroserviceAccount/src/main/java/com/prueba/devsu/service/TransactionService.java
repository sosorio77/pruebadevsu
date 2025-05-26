package com.prueba.devsu.service;

import com.prueba.devsu.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> findTransactionAll();
    TransactionDTO createTransaction(TransactionDTO dto);

}
