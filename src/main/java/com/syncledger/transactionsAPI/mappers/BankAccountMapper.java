package com.syncledger.transactionsAPI.mappers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccount mapToEntity(com.plaid.client.model.AccountBase plaidBankAccount ) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountId(plaidBankAccount.getAccountId());
        bankAccount.setAccountName(plaidBankAccount.getName());
        bankAccount.setAccountOfficialName(plaidBankAccount.getOfficialName());
        bankAccount.setType(plaidBankAccount.getType().name());
        bankAccount.setSubType(plaidBankAccount.getSubtype().name());
        bankAccount.setBalance(plaidBankAccount.getBalances().getCurrent());
        return bankAccount;
    }
}
