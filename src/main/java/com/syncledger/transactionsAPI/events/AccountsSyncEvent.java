package com.syncledger.transactionsAPI.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.concurrent.CompletableFuture;

@Getter
public class AccountsSyncEvent extends ApplicationEvent {
    private final String itemId;
    private final CompletableFuture<Boolean> completionFuture;

    public AccountsSyncEvent(Object source, String itemId, CompletableFuture<Boolean> completionFuture) {
        super(source);
        this.itemId = itemId;
        System.out.println("AccountsSyncEvent created");
        this.completionFuture = completionFuture;
    }
}
