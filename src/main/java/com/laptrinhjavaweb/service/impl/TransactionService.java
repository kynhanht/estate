package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.ErrorMessageConstants;
import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.request.TransactionRequest;
import com.laptrinhjavaweb.dto.respone.TransactionResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.enums.TransactionEnum;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    public List<TransactionResponse> getTransactionsByCustomerId(Long customerId) {

        List<TransactionEntity> transactionEntities = transactionRepository.findByCustomer_Id(customerId);
        List<TransactionResponse> responses = new ArrayList<>();

        Arrays.stream(TransactionEnum.values())
                .forEach(transactionEnum -> {
                    TransactionResponse response = new TransactionResponse();
                    response.setCode(transactionEnum.toString());
                    response.setTransactionValue(transactionEnum.getTransactionValue());
                    responses.add(response);

                });

        responses.forEach(response ->{
            String code = response.getCode();
            transactionEntities.stream()
                    .filter(transaction -> code.equals(transaction.getCode()))
                    .forEach(transaction -> {
                        TransactionDTO transactionDTO = transactionConverter.convertToDTO(transaction);
                        response.getTransactions().add(transactionDTO);
                    });

            });

        return responses;

    }

    @Override
    public TransactionDTO createTransaction(TransactionRequest transactionRequest) {

        CustomerEntity customerEntity = customerRepository.findById(transactionRequest.getCustomerId())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCode(transactionRequest.getCode());
        transactionEntity.setNote(transactionRequest.getNote());
        transactionEntity.setCustomer(customerEntity);
        return transactionConverter.convertToDTO(transactionRepository.save(transactionEntity));
    }
}
