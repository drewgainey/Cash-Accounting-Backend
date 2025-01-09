package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.repositories.BankAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/banks")
public class BanksController {

    @Autowired
    private BankAccountRespository bankAccountRespository;

    @GetMapping("/accounts")
    public List<BankAccount> getBankAccounts() {
        return bankAccountRespository.findAll();
    }
}
