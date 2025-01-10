package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    @Query("SELECT b FROM BankAccount b JOIN FETCH b.defaultFields")
    List<BankAccount> findAllBankAccountsWithDefaults();
}
