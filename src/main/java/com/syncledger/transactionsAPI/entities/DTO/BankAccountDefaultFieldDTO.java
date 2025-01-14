package com.syncledger.transactionsAPI.entities.DTO;

import lombok.Data;

@Data
public class BankAccountDefaultFieldDTO {
    private String fieldName;
    private Long fieldId;
    private String fieldValue;
    private Long fieldValueId;

    public BankAccountDefaultFieldDTO(String fieldName, Long fieldId, String fieldValue, Long fieldValueId) {
        this.fieldName = fieldName;
        this.fieldId = fieldId;
        this.fieldValue = fieldValue;
        this.fieldValueId = fieldValueId;
    }
}
