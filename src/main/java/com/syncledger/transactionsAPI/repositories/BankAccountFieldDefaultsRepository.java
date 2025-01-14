package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.BankAccountFieldDefaults;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountFieldDefaultsRepository extends JpaRepository<BankAccountFieldDefaults, String> {


    Optional<BankAccountFieldDefaults> findByBankAccountIdAndAccountingFieldId(Long bankAccount_id, long accountingField_id);
}
