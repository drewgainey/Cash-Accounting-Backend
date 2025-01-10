package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.AccountingField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountingFieldRepository extends JpaRepository<AccountingField, Long> {
}
