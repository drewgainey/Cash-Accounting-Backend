package com.syncledger.transactionsAPI.services;

import com.plaid.client.model.AccountBase;
import com.plaid.client.model.AccountsGetRequest;
import com.plaid.client.model.AccountsGetResponse;
import com.plaid.client.request.PlaidApi;
import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.mappers.BankAccountMapper;
import com.syncledger.transactionsAPI.repositories.BankAccountRepository;
import com.syncledger.transactionsAPI.repositories.PlaidItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaidAccountService {
    @Autowired
    private PlaidApi plaidApi;

    @Autowired
    private PlaidItemRepository plaidItemRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private BankAccountMapper bankAccountMapper;

    @Transactional
    public void syncPlaidBankAccounts(String itemId) throws IOException {
        String accessToken = plaidItemRepository.findAccessTokenByPlaidItemId(itemId);

        AccountsGetRequest request = new AccountsGetRequest()
                .accessToken(accessToken);

        Response<AccountsGetResponse> response = plaidApi.accountsGet(request).execute();

        if(response.isSuccessful()) {
            List<AccountBase> plaidAccounts = response.body().getAccounts();
            List<BankAccount> bankAccountsToSave = plaidAccounts.stream()
                    .map(bankAccountMapper::mapToEntity)
                    .collect(Collectors.toList());
            bankAccountRepository.saveAll(bankAccountsToSave);
        }
    }
}
