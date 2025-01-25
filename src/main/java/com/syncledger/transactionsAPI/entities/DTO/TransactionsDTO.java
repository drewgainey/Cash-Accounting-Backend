package com.syncledger.transactionsAPI.entities.DTO;

import com.syncledger.transactionsAPI.entities.BankAccount;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionsDTO {

    private String transactionId;
    private String merchantName;
    private String category;
    private Double amount;
    private String currency;
    private LocalDate date;
    private String accountId;
    private String accountName;
}
