package com.syncledger.transactionsAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    //Should always start with afv_
    private String internalId;

    private String fieldValue;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    @JsonBackReference
    private AccountingField accountingField;
}
