package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.TransactionRequest;
import com.laptrinhjavaweb.dto.respone.TransactionResponse;
import com.laptrinhjavaweb.entity.TransactionEntity;
import org.hibernate.Transaction;

import java.util.List;

public interface ITransactionService {

    List<TransactionResponse> getTransactionsByCustomerId(Long customerId);

    TransactionDTO createTransaction(TransactionRequest transactionRequest);
}
