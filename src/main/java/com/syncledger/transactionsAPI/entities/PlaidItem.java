package com.syncledger.transactionsAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
public class PlaidItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "plaid_item_id", unique = true, nullable = false)
    private String plaidItemId;

    @Column(name = "plaid_access_token", unique = true, nullable = false)
    private String plaidAccessToken;

    @Column(name = "transactions_cursor")
    private String transactionsCursor;
}
