package com.syncledger.transactionsAPI.listeners;

import com.syncledger.transactionsAPI.events.TransactionsSyncEvent;
import com.syncledger.transactionsAPI.repositories.PlaidItemRepository;
import com.syncledger.transactionsAPI.services.PlaidTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionsSyncListener {
    @Autowired
    private PlaidTransactionService plaidTransactionService;

    @Autowired
    private PlaidItemRepository plaidItemRepository;

    @EventListener
    public void onTransactionsSyncEvent(TransactionsSyncEvent event) throws IOException {
        String itemId = event.getItemId();
        String accessToken = plaidItemRepository.findAccessTokenByPlaidItemId(itemId);

        if(accessToken != null) {
           plaidTransactionService.syncTransactionsAndSave(itemId, accessToken);
        }
    }
}
