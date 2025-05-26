package com.prueba.devsu.controller;

import com.prueba.devsu.dto.TransactionDTO;
import com.prueba.devsu.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionDTO> findTransactionAll() {
        return service.findTransactionAll();
    }

    @PostMapping
    public TransactionDTO createTransaction(@RequestBody TransactionDTO dto) {
        return service.createTransaction(dto);
    }


}
