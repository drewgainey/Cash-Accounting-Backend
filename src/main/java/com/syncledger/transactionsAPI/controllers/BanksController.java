package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.entities.response.BankAccountsGetResponseDTO;
import com.syncledger.transactionsAPI.mappers.BankAccountGetResponseDTOMapper;
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

    @Autowired
    private BankAccountGetResponseDTOMapper bankAccountGetResponseDTOMapper;

    @GetMapping("/accounts")
    public List<BankAccountsGetResponseDTO> getBankAccounts() {
       List<BankAccount> banks = bankAccountRepository.findAllBankAccountsWithDefaults();
       System.out.println(banks);
       return banks.stream().map(bankAccountGetResponseDTOMapper::mapToDTO).toList();
    }
}
