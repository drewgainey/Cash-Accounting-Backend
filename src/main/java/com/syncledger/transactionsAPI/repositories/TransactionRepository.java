package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
