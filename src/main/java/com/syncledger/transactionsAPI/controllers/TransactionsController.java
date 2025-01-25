package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.DTO.TransactionsDTO;
import com.syncledger.transactionsAPI.entities.Transaction;
import com.syncledger.transactionsAPI.entities.response.APIResponse;
import com.syncledger.transactionsAPI.mappers.TransactionDTOMapper;
import com.syncledger.transactionsAPI.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/transactions")
public class TransactionsController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDTOMapper transactionDTOMapper;

    @GetMapping("/get")
    public APIResponse<List<TransactionsDTO>> getAllTransactions() {
        try {
        System.out.println("Transactions Request Processing");
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionsDTO> data = transactions.stream().map(transactionDTOMapper::mapToDTO).toList();
        return new APIResponse<>(
                "200",
                "success",
                data,
                null
        ); }
        catch(Exception e) {
            return new APIResponse<>(
                    "400",
                    "error",
                    null,
                    null
            );
        }
    }
}
