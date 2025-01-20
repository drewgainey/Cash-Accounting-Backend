package com.syncledger.transactionsAPI.entities.request;

import com.syncledger.transactionsAPI.entities.DTO.BankAccountDefaultFieldPutDTO;
import lombok.Data;
import java.util.List;

@Data
public class BankAccountUpdateRequest {
    private List<BankAccountDefaultFieldPutDTO> banksToUpdate;
}
