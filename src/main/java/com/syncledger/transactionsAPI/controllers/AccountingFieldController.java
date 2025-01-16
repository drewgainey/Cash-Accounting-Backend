package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.AccountingField;
import com.syncledger.transactionsAPI.entities.response.APIResponse;
import com.syncledger.transactionsAPI.repositories.AccountingFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/accounting-fields")
public class AccountingFieldController {

    @Autowired
    private AccountingFieldRepository accountingFieldRepository;

    @GetMapping
    public APIResponse<List<AccountingField>> getAllAccountingFields() {
        return new APIResponse<>(
                "200",
                "success",
                accountingFieldRepository.findAll(),
                null
        );
    }
}
