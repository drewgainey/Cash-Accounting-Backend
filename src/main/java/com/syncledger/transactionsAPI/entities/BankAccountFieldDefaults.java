package com.syncledger.transactionsAPI.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_account_field_defaults")
public class BankAccountFieldDefaults {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;

    @ManyToOne
    @JoinColumn(name = "accounting_field_value_id", nullable = false)
    private AccountingFieldValue fieldValue;

}
