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

    @PostMapping("/exchange-token")
    public ResponseEntity<String> exchangeToken(@RequestBody LinkTokenExchangeRequest linkTokenExchangeRequest) {
        try {
            String itemId = plaidLinkService.exchangeLinkToken(linkTokenExchangeRequest.getPublicToken());
            eventPublisher.publishEvent(new TransactionsSyncEvent(this, itemId));
            eventPublisher.publishEvent(new AccountsSyncEvent(this, itemId));
            return ResponseEntity.ok().body("Token exchanged and transactions are being synchronized");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
