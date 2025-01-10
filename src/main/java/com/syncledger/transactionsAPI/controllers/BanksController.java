package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/banks")
public class BanksController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @GetMapping("/accounts")
    public List<BankAccount> getBankAccounts() {
        System.out.println("Getting bank accounts");
        return bankAccountRepository.findAllBankAccountsWithDefaults();
    }
}
