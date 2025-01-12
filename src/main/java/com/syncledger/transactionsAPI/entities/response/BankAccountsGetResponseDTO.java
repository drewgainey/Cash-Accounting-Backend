package com.syncledger.transactionsAPI.entities.response;

import lombok.Data;

import java.util.List;

@Data
public class BankAccountsGetResponseDTO {
    private Long id;
    private String accountId;
    private String accountName;
    private String accountOfficialName;
    private String type;
    private String subType;
    private double balance;
    private List<BankAccountDefaultFieldDTO> defaultFields;
}


