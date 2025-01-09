package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRespository extends JpaRepository<BankAccount, Long> {
}
