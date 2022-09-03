package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.respone.CustomerSearchResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConveter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDTO(CustomerEntity customerEntity) {

        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }

    public CustomerEntity convertToEntity(CustomerDTO customerDTO) {

        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        return customerEntity;
    }

    public CustomerSearchResponse convertToCustomerSearchResponse(CustomerEntity customerEntity) {

        CustomerSearchResponse response = modelMapper.map(customerEntity, CustomerSearchResponse.class);
        response.setModifiedDate(DateUtils.convertDateToString(customerEntity.getModifiedDate()));
        return response;
    }

}
