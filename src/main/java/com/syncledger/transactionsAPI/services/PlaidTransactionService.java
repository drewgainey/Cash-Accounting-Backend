package com.syncledger.transactionsAPI.services;

import com.plaid.client.model.*;
import com.plaid.client.request.PlaidApi;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.entities.Transaction;
import com.syncledger.transactionsAPI.mappers.TransactionMapper;
import com.syncledger.transactionsAPI.repositories.BankAccountRepository;
import com.syncledger.transactionsAPI.repositories.PlaidItemRepository;
import com.syncledger.transactionsAPI.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class PlaidTransactionService {
    @Autowired
    private PlaidApi plaidApi;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private PlaidItemRepository plaidItemRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional
    public Future<Boolean> syncTransactionsAndSave(String itemId, String accessToken) throws IOException {
        boolean hasMore = true;
        String cursor = plaidItemRepository.findTransactionsCursorByPlaidItemId(itemId);

        TransactionsSyncRequestOptions options = new TransactionsSyncRequestOptions()
                .includePersonalFinanceCategory(true);

        while(hasMore) {
            TransactionsSyncRequest request = new TransactionsSyncRequest()
                    .accessToken(accessToken)
                    .cursor(cursor)
                    .options(options);

           Response<TransactionsSyncResponse> response = plaidApi.transactionsSync(request).execute();
           if(response.isSuccessful()) {
               List<com.plaid.client.model.Transaction> plaidTransactions = response.body().getAdded();
               @NotNull List<Transaction> transactionsToSave = plaidTransactions.stream()
                       .map(plaidTransaction -> {
                           // Retrieve or create the BankAccount associated with this transaction
                           BankAccount bankAccount = bankAccountRepository.findByAccountId(plaidTransaction.getAccountId())
                                   .orElseThrow(() -> new IllegalStateException("Bank account not found for ID: " + plaidTransaction.getAccountId()));
                           return transactionMapper.mapToEntity(plaidTransaction, bankAccount);
                       })
                       .collect(Collectors.toList());

               transactionRepository.saveAll(transactionsToSave);

               cursor = response.body().getNextCursor();
               hasMore = response.body().getHasMore();

           }
        }

//        Save Cursor
        int updatedItemsCount = plaidItemRepository.updateTransactionsCursorByPlaidItemId(itemId, cursor);
        if(updatedItemsCount > 1) {
            System.out.println("Something went wrong...");
        }

        return AsyncResult.forValue(true);
    }
}
