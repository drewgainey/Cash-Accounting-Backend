package com.syncledger.transactionsAPI.entities.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkTokenExchangeRequest {
    private String publicToken;
}
