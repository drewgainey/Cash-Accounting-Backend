package com.syncledger.transactionsAPI.services;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.entities.BankAccountFieldDefaults;
import com.syncledger.transactionsAPI.entities.DTO.BankAccountDefaultFieldDTO;
import com.syncledger.transactionsAPI.repositories.AccountingFieldRepository;
import com.syncledger.transactionsAPI.repositories.AccountingFieldValueRepository;
import com.syncledger.transactionsAPI.repositories.BankAccountFieldDefaultsRepository;
import com.syncledger.transactionsAPI.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountFieldDefaultsRepository bankAccountFieldDefaultsRepository;

    @Autowired
    private AccountingFieldRepository accountingFieldRepository;

    @Autowired
    private AccountingFieldValueRepository accountingFieldValueRepository;


    @Transactional
    public boolean updateDefaultFields(Long bankAccountId, List<BankAccountDefaultFieldDTO> defaultFields) {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(bankAccountId);
        if(bankAccountOptional.isEmpty()) {
            return false;
        }

        BankAccount bankAccount = bankAccountOptional.get();

        for(BankAccountDefaultFieldDTO defaultField : defaultFields) {
            Optional<BankAccountFieldDefaults> existingDefaultField = bankAccountFieldDefaultsRepository
                    .findByBankAccountIdAndAccountingFieldId(bankAccountId, defaultField.getFieldId());
            BankAccountFieldDefaults fieldDefault = existingDefaultField.orElseGet(BankAccountFieldDefaults::new);

            fieldDefault.setBankAccount(bankAccount);
            fieldDefault.setAccountingField(accountingFieldRepository.getOne(defaultField.getFieldId()));
            fieldDefault.setFieldValue(accountingFieldValueRepository.getOne(defaultField.getFieldValueId()));
            bankAccountFieldDefaultsRepository.save(fieldDefault);
        }

        return true;
    }
}
