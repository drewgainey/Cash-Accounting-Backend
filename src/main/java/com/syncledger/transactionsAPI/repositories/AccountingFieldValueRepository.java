package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.AccountingFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountingFieldValueRepository extends JpaRepository<AccountingFieldValue, Long> {
}
