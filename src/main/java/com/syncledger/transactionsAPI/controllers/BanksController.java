package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.entities.DTO.BankAccountDefaultFieldPutDTO;
import com.syncledger.transactionsAPI.entities.request.BankAccountUpdateRequest;
import com.syncledger.transactionsAPI.entities.response.APIResponse;
import com.syncledger.transactionsAPI.entities.DTO.BankAccountsGetResponseDTO;
import com.syncledger.transactionsAPI.mappers.BankAccountGetResponseDTOMapper;
import com.syncledger.transactionsAPI.repositories.BankAccountRepository;
import com.syncledger.transactionsAPI.services.BankAccountService;
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

    @Autowired
    private BankAccountService bankAccountService;

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

    @PutMapping("/account-defaults")
    public APIResponse<String> updateAccountDefaults(@RequestBody BankAccountUpdateRequest request) {
        System.out.println("Updating default bank accounts");
        for (BankAccountDefaultFieldPutDTO bank : request.getBanksToUpdate()) {
            boolean updateSuccessful = bankAccountService.updateDefaultFields(bank.getBankAccountId(), bank.getDefaultFields());
            if (!updateSuccessful) {
                return new APIResponse<>(
                        "400",
                        "failure",
                        null,
                        null
                );
            }
        }
        return new APIResponse<>(
                "200",
                "success",
                null,
                null
        );
    }
}
