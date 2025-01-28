package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.ERPTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ERPTransactionRepository extends JpaRepository<ERPTransaction, Long> {
}
