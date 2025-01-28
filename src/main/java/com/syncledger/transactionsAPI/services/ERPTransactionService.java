package com.syncledger.transactionsAPI.services;

import com.syncledger.transactionsAPI.entities.ERPTransaction;
import com.syncledger.transactionsAPI.mappers.CsvToERPTransactionMapper;
import com.syncledger.transactionsAPI.repositories.ERPTransactionRepository;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ERPTransactionService {
    @Autowired
    private ERPTransactionRepository erpTransactionRepository;

    @Autowired
    private CsvToERPTransactionMapper csvToERPTransactionMapper;

    public void csvToDatabase(Iterable<CSVRecord> records) {
        try {
        List<ERPTransaction> erpTransactions = StreamSupport.stream(records.spliterator(), false)
                .map(csvToERPTransactionMapper::mapToERPTransaction)
                .toList();
        erpTransactionRepository.saveAll(erpTransactions); }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
