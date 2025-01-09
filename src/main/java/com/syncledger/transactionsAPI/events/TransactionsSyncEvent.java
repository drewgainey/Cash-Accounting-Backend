package com.syncledger.transactionsAPI.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TransactionsSyncEvent extends ApplicationEvent {
    private final String itemId;

    public TransactionsSyncEvent(Object source, String itemId) {
        super(source);
        this.itemId = itemId;
        System.out.println("TransactionsSyncEvent created");
    }
}
