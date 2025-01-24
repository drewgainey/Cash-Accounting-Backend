package com.syncledger.transactionsAPI.mappers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public Transaction mapToEntity(com.plaid.client.model.Transaction plaidTransaction, BankAccount bankAccount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(plaidTransaction.getTransactionId());
        transaction.setMerchantName(plaidTransaction.getMerchantName());
        transaction.setCategory(plaidTransaction.getCategory() != null
                ? String.join(", ", plaidTransaction.getCategory())
                : null);
        transaction.setAmount(plaidTransaction.getAmount());
        transaction.setCurrency(plaidTransaction.getIsoCurrencyCode());
        transaction.setDate(plaidTransaction.getDate());
        transaction.setBankAccount(bankAccount);
        return transaction;
    }
}
