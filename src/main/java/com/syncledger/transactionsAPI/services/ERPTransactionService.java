package com.syncledger.transactionsAPI.services;

import com.syncledger.transactionsAPI.entities.DTO.ERPTransactionsDTO;
import com.syncledger.transactionsAPI.entities.ERPTransaction;
import com.syncledger.transactionsAPI.mappers.CsvToERPTransactionMapper;
import com.syncledger.transactionsAPI.repositories.ERPTransactionRepository;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<ERPTransactionsDTO> getAllERPTransactions() {
        List<ERPTransaction> erpTransactions = erpTransactionRepository.findAll();
        return erpTransactions.stream().map(trans -> {
            ERPTransactionsDTO erpTransactionsDTO = new ERPTransactionsDTO();
            erpTransactionsDTO.setErpTransactionId(trans.getErpTransactionId());
            erpTransactionsDTO.setDescription(trans.getDescription());
            erpTransactionsDTO.setAmount(trans.getAmount());
            return erpTransactionsDTO;
        }).toList();
    }
}