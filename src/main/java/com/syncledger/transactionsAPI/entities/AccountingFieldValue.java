package com.syncledger.transactionsAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "accounting_field_values")
@Getter
@Setter
public class AccountingFieldValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fieldValue;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private AccountingField accountingField;
}
