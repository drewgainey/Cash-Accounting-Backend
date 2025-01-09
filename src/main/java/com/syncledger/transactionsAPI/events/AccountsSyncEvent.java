package com.syncledger.transactionsAPI.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AccountsSyncEvent extends ApplicationEvent {
    private final String itemId;

    public AccountsSyncEvent(Object source, String itemId) {
        super(source);
        this.itemId = itemId;
        System.out.println("AccountsSyncEvent created");

    }
}
