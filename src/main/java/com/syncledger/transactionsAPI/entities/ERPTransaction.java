package com.syncledger.transactionsAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "erp_transactions")
@Getter
@Setter
public class ERPTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String erpTransactionId;
    private Double amount;
    private String description;

}
