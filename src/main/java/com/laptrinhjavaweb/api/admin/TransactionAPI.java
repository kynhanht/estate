package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.TransactionRequest;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("transactionAPIOfAdmin")
@RequestMapping("/api/transaction")
public class TransactionAPI {

    @Autowired
    private ITransactionService transactionService;


    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionRequest transactionRequest){
        return  ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionRequest));
    }
}
