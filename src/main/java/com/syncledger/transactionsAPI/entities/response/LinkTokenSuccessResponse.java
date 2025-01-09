package com.syncledger.transactionsAPI.entities.response;

import lombok.Getter;

@Getter
public class LinkTokenSuccessResponse {
    private final String linkToken;

    public LinkTokenSuccessResponse(String linkToken) {
        this.linkToken = linkToken;
    }
}
