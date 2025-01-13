package com.syncledger.transactionsAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bank_account_field_defaults", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"bank_account_id", "accounting_field_id"})
})
public class BankAccountFieldDefaults {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;

    @ManyToOne
    @JoinColumn(name = "accounting_field_id", nullable = false)
    private AccountingField accountingField;

    @ManyToOne
    @JoinColumn(name = "accounting_field_value_id", nullable = true)
    @JsonBackReference
    private AccountingFieldValue fieldValue;

}
