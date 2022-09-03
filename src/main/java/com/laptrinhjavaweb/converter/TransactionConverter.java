package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO convertToDTO(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        transactionDTO.setCreatedBy(DateUtils.convertFullDateToString(transactionEntity.getCreatedDate()));
        return transactionDTO;
    }
}
