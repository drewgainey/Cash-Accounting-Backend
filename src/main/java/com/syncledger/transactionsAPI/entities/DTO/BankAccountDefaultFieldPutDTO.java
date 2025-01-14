package com.syncledger.transactionsAPI.entities.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BankAccountDefaultFieldPutDTO {
    private Long bankAccountId;
    private List<BankAccountDefaultFieldDTO> defaultFields;
}
