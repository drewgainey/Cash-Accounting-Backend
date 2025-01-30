package com.syncledger.transactionsAPI.controllers;

import com.syncledger.transactionsAPI.entities.DTO.ERPTransactionsDTO;
import com.syncledger.transactionsAPI.entities.DTO.TransactionsDTO;
import com.syncledger.transactionsAPI.entities.Transaction;
import com.syncledger.transactionsAPI.entities.response.APIResponse;
import com.syncledger.transactionsAPI.mappers.TransactionDTOMapper;
import com.syncledger.transactionsAPI.repositories.TransactionRepository;
import com.syncledger.transactionsAPI.services.ERPTransactionService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/v1/api/transactions")
public class TransactionsController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDTOMapper transactionDTOMapper;

    @Autowired
    private ERPTransactionService erpTransactionService;

    @GetMapping("/get")
    public APIResponse<List<TransactionsDTO>> getAllTransactions() {
        try {
        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionsDTO> data = transactions.stream().map(transactionDTOMapper::mapToDTO).toList();
        return new APIResponse<>(
                "200",
                "success",
                data,
                null
        ); }
        catch(Exception e) {
            return new APIResponse<>(
                    "400",
                    "error",
                    null,
                    null
            );
        }
    }

    @GetMapping
    public APIResponse<List<ERPTransactionsDTO>> getAllERPTransactions() {
        List<ERPTransactionsDTO> dto = erpTransactionService.getAllERPTransactions();
        return new APIResponse<>(
                "200",
                "success",
                dto,
                null
        );
    }

    @PostMapping("/erp_upload")
    public APIResponse<String> uploadErpTransactions(@RequestParam("file") MultipartFile file) {
      if (file.isEmpty()) {
          return new APIResponse<>(
                  "500",
                  "error",
                  null,
                  null
          );
      }

      try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
          if (reader.markSupported()) {
              reader.mark(1);
              if (reader.read() != 0xFEFF) {
                  reader.reset(); // not the BOM marker
              }
          }
          Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

          erpTransactionService.csvToDatabase(records);
          return new APIResponse<>(
                  "400",
                  "sucess",
                  null,
                  null
          );
      } catch (Exception e) {
          return new APIResponse<>(
                  "500",
                  "error",
                  null,
                  null
          );
      }
  }
}
