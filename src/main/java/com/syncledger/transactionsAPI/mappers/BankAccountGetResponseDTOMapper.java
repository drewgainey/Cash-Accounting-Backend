package com.syncledger.transactionsAPI.mappers;

import com.syncledger.transactionsAPI.entities.BankAccount;
import com.syncledger.transactionsAPI.entities.DTO.BankAccountDefaultFieldDTO;
import com.syncledger.transactionsAPI.entities.DTO.BankAccountsGetResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankAccountGetResponseDTOMapper {
    public BankAccountsGetResponseDTO mapToDTO(BankAccount bankAccount) {
        BankAccountsGetResponseDTO dto = new BankAccountsGetResponseDTO();
        dto.setId(bankAccount.getId());
        dto.setAccountId(bankAccount.getAccountId());
        dto.setAccountName(bankAccount.getAccountName());
        dto.setAccountOfficialName(bankAccount.getAccountOfficialName());
        dto.setType(bankAccount.getType());
        dto.setSubType(bankAccount.getSubType());
        dto.setBalance(bankAccount.getBalance());
        List<BankAccountDefaultFieldDTO> defaults = bankAccount.getDefaultFields().stream()
                .map(defaultField -> new BankAccountDefaultFieldDTO(
                    defaultField.getAccountingField().getFieldName(),
                    defaultField.getAccountingField().getId(),
                    defaultField.getFieldValue().getFieldValue(),
                    defaultField.getFieldValue().getId())
        ).collect(Collectors.toList());
        dto.setDefaultFields(defaults);
        return dto;
    }
}
