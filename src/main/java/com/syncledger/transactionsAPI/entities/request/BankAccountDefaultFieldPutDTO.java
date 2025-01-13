package com.syncledger.transactionsAPI.entities.request;

import com.syncledger.transactionsAPI.entities.response.BankAccountDefaultFieldDTO;
import lombok.Data;

import java.util.List;

@Data
public class BankAccountDefaultFieldPutDTO {
    private Long bankAccountId;
    private List<BankAccountDefaultFieldDTO> defaultFields;
}
