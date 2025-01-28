package com.syncledger.transactionsAPI.mappers;

import com.syncledger.transactionsAPI.entities.ERPTransaction;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

@Component
public class CsvToERPTransactionMapper {
    public ERPTransaction mapToERPTransaction(CSVRecord record) {
        ERPTransaction transaction = new ERPTransaction();
        transaction.setErpTransactionId(getValue(record, "transaction_id"));
        transaction.setAmount(Double.parseDouble(getValue(record, "amount")));
        transaction.setDescription(getValue(record, "description"));
        return transaction;
    }

    private String getValue(CSVRecord record, String header) {
        // Attempt to fetch the value by header, cleaning header name if necessary
        return record.isMapped(header) ? record.get(header) :
                record.get(header.trim()); // Trim the header in case of spaces
    }
}