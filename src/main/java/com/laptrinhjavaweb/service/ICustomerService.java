package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerRequest;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequest;
import com.laptrinhjavaweb.dto.respone.CustomerSearchResponse;
import com.laptrinhjavaweb.dto.respone.PaginationResponse;

import java.util.List;

public interface ICustomerService {

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomers(List<Long> ids);

    PaginationResponse<CustomerSearchResponse> searchCustomers(CustomerSearchRequest request);

    void assignCustomer(AssignmentCustomerRequest request);
}
