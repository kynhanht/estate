package com.laptrinhjavaweb.dto.respone;

import com.laptrinhjavaweb.dto.TransactionDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponse implements Serializable {

    private String code;

    private String transactionValue;

    private List<TransactionDTO> transactions = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

}
