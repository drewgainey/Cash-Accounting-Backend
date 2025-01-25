package com.syncledger.transactionsAPI.mappers;

import com.syncledger.transactionsAPI.entities.DTO.TransactionsDTO;
import com.syncledger.transactionsAPI.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDTOMapper {
    public TransactionsDTO mapToDTO(Transaction transaction) {
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setTransactionId(transaction.getTransactionId());
        transactionsDTO.setMerchantName(transaction.getMerchantName());
        transactionsDTO.setCategory(transaction.getCategory());
        transactionsDTO.setAmount(transaction.getAmount());
        transactionsDTO.setCurrency(transaction.getCurrency());
        transactionsDTO.setDate(transaction.getDate());
        transactionsDTO.setAccountId(transaction.getBankAccount().getAccountId());
        transactionsDTO.setAccountName(transaction.getBankAccount().getAccountOfficialName());
        return transactionsDTO;
    }
}
