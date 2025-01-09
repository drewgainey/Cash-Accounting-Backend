package com.syncledger.transactionsAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transactionId;
    private String accountId;
    private String merchantName;
    private String category;
    private Double amount;
    private String currency;
    private LocalDate date;
}
