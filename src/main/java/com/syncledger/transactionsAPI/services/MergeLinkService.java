package com.syncledger.transactionsAPI.services;

import com.merge.api.MergeApiClient;
import com.merge.api.resources.accounting.types.LinkToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MergeLinkService {
    @Autowired
    private MergeApiClient mergeApiClient;

    public String createLinkToken() {
        LinkToken linkToken = mergeApiClient.accounting().linkToken().create(null);
        return linkToken.getLinkToken();
    }
}
