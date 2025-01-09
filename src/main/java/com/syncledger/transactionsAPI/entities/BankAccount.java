package com.syncledger.transactionsAPI.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bank_accounts")
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    //the account id has potential to change....look into this in the API documents
    private String accountId;
    private String accountName;
    private String accountOfficialName;
    private String plaidItemId;
    private String type;
    private String subType;
    private double balance;
}
