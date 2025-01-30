package com.syncledger.transactionsAPI.entities.DTO;

import lombok.Data;

@Data
public class ERPTransactionsDTO {
    private String erpTransactionId;
    private Double amount;
    private String description;
}
