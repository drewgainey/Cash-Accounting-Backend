package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.request.LinkTokenExchangeRequest;
import com.syncledger.transactionsAPI.entities.request.LinkTokenRequest;
import com.syncledger.transactionsAPI.entities.response.LinkTokenSuccessResponse;
import com.syncledger.transactionsAPI.events.AccountsSyncEvent;
import com.syncledger.transactionsAPI.events.TransactionsSyncEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.syncledger.transactionsAPI.services.PlaidLinkService;

import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/v1/api/link")
public class LinkController {

    @Autowired
    private PlaidLinkService plaidLinkService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody LinkTokenRequest linkTokenRequest) {
        try {
            String linkToken = plaidLinkService.createLinkToken();
            LinkTokenSuccessResponse linkTokenSuccessResponse = new LinkTokenSuccessResponse(linkToken);
            return ResponseEntity.ok(linkTokenSuccessResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

//    @PostMapping("/exchange-token")
//    public ResponseEntity<String> exchangeToken(@RequestBody LinkTokenExchangeRequest linkTokenExchangeRequest) {
//        try {
//            String itemId = plaidLinkService.exchangeLinkToken(linkTokenExchangeRequest.getPublicToken());
//            eventPublisher.publishEvent(new AccountsSyncEvent(this, itemId));
//            eventPublisher.publishEvent(new TransactionsSyncEvent(this, itemId));
//            return ResponseEntity.ok().body("Token exchanged and transactions are being synchronized");
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//    }
@PostMapping("/exchange-token")
public ResponseEntity<String> exchangeToken(@RequestBody LinkTokenExchangeRequest linkTokenExchangeRequest) {
    try {
        String itemId = plaidLinkService.exchangeLinkToken(linkTokenExchangeRequest.getPublicToken());
        CompletableFuture<Boolean> accountsSyncComplete = new CompletableFuture<>();
        eventPublisher.publishEvent(new AccountsSyncEvent(this, itemId, accountsSyncComplete));

        // Listen for the completion and proceed based on the result asynchronously
        accountsSyncComplete.thenAccept(success -> {
            if (success) {
                eventPublisher.publishEvent(new TransactionsSyncEvent(this, itemId));
                notifyUI("success", "All tasks completed successfully.");
            } else {
                notifyUI("error", "Accounts sync failed.");
            }
        }).exceptionally(ex -> {
            notifyUI("error", "Error during AccountsSync: " + ex.getMessage());
            return null;
        });

        return ResponseEntity.accepted().body("Token exchange initiated. Check notifications for completion status.");
    } catch (Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}

    private void notifyUI(String status, String message) {
        // Method to notify the UI, could be via WebSocket, SSE, or any other mechanism
        System.out.println(status + " " +message);
    }
}
