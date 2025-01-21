package com.syncledger.transactionsAPI.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "accounting_fields")
@Getter
@Setter
public class AccountingField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Should always start with af_
    private String internalId;

    private String fieldName;

    @OneToMany(mappedBy = "accountingField")
    @JsonManagedReference
    private List<AccountingFieldValue> values;
}
