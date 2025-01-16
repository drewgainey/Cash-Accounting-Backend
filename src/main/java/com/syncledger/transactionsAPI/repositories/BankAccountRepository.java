package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query("SELECT DISTINCT b FROM BankAccount b LEFT JOIN FETCH b.defaultFields df LEFT JOIN FETCH df.accountingField LEFT JOIN FETCH df.fieldValue")
    List<BankAccount> findAllBankAccountsWithDefaults();
}
