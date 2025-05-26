package com.prueba.devsu.controller;

import com.prueba.devsu.dto.AccountDto;
import com.prueba.devsu.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public AccountDto findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @GetMapping
    public List<AccountDto> findAccountAll() {
        return accountService.findAccountAll();
    }

    @PostMapping
    public AccountDto createAccount(@RequestBody AccountDto dto) {
        return accountService.createAccount(dto);
    }



    @PutMapping("/update/{id}")
    public AccountDto updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
        return accountService.updateAccount(id, dto);
    }


}
