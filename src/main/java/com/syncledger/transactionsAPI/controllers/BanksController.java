package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.entities.request.BankAccountDefaultFieldPutDTO;
import com.syncledger.transactionsAPI.entities.response.APIResponse;
import com.syncledger.transactionsAPI.entities.response.BankAccountsGetResponseDTO;
import com.syncledger.transactionsAPI.mappers.BankAccountGetResponseDTOMapper;
import com.syncledger.transactionsAPI.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/banks")
public class BanksController {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountGetResponseDTOMapper bankAccountGetResponseDTOMapper;

    @GetMapping("/accounts")
    public APIResponse<List<BankAccountsGetResponseDTO>> getBankAccounts() {
       List<BankAccount> banks = bankAccountRepository.findAllBankAccountsWithDefaults();
       System.out.println(banks);
       List<BankAccountsGetResponseDTO> data = banks.stream().map(bankAccountGetResponseDTOMapper::mapToDTO).toList();
       return new APIResponse<>(
               "200",
               "success",
               data,
               null
       );
    }

    @PostMapping("/account-defaults")
    public APIResponse<String> updateAccountDefaults(@RequestBody BankAccountDefaultFieldPutDTO bankAccountDefaultFieldPutDTO) {
        
        return new APIResponse<>(
                "200",
                "success",
                null,
                null
        );
    }
}
