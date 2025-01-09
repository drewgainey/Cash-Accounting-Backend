package com.syncledger.transactionsAPI.repositories;

import com.syncledger.transactionsAPI.entities.PlaidItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaidItemRepository extends JpaRepository<PlaidItem, Long> {
    @Query("SELECT p.transactionsCursor FROM PlaidItem p WHERE p.plaidItemId = ?1")
    String findTransactionsCursorByPlaidItemId(String plaidItemId);


    @Transactional
    @Modifying
    @Query("UPDATE PlaidItem p SET p.transactionsCursor = ?2 WHERE p.plaidItemId = ?1")
    int updateTransactionsCursorByPlaidItemId(String plaidItemId, String transactionsCursor);

    @Query("SELECT p.plaidAccessToken FROM PlaidItem p WHERE p.plaidItemId = ?1")
    String findAccessTokenByPlaidItemId(String itemId);
}