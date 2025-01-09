package com.syncledger.transactionsAPI.listeners;

import com.syncledger.transactionsAPI.events.AccountsSyncEvent;
import com.syncledger.transactionsAPI.services.PlaidAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccountsSyncEventListener {

    @Autowired
    PlaidAccountService plaidAccountService;

    @EventListener
    public void onAccountsSyncEvent(AccountsSyncEvent event) throws IOException {
        String  itemId = event.getItemId();
        plaidAccountService.syncPlaidBankAccounts(itemId);
    }
}
