package com.syncledger.transactionsAPI.services;

import com.plaid.client.model.*;
import com.plaid.client.request.PlaidApi;
import com.syncledger.transactionsAPI.entities.PlaidItem;
import com.syncledger.transactionsAPI.repositories.PlaidItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Service
public class PlaidLinkService {
    @Autowired
    private PlaidApi plaidApi;

    @Autowired
    private PlaidItemRepository plaidItemRepository;

    public String createLinkToken() throws IOException {
        LinkTokenCreateRequestUser user = new LinkTokenCreateRequestUser()
                .clientUserId("client id");

        LinkTokenCreateRequest request = new LinkTokenCreateRequest()
                .clientName("Sync Ledgers")
                .language("en")
                .countryCodes(List.of(CountryCode.US))
                .products(List.of(Products.TRANSACTIONS))
                .user(user);
        Response<LinkTokenCreateResponse> response = plaidApi.linkTokenCreate(request).execute();

        if(response.isSuccessful()) {
            return response.body().getLinkToken();
        } else {
            throw new IOException("Failed to create link token");
        }
    }

    public String exchangeLinkToken(String publicToken) throws IOException {
        ItemPublicTokenExchangeRequest request = new ItemPublicTokenExchangeRequest()
                .publicToken(publicToken);
        Response<ItemPublicTokenExchangeResponse> response = plaidApi.itemPublicTokenExchange(request).execute();
        if (response.isSuccessful()) {
            String itemId = response.body().getItemId();
            String accessToken = response.body().getAccessToken();

            PlaidItem plaidItem = new PlaidItem();
            plaidItem.setPlaidItemId(itemId);
            plaidItem.setPlaidAccessToken(accessToken);

            plaidItemRepository.save(plaidItem);
            return itemId;
        } else {
            throw new IOException("Link token exchange failed");
        }
    }
}