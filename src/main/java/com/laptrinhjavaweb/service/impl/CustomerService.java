package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.constant.ErrorMessageConstants;
import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.converter.CustomerConveter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerRequest;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequest;
import com.laptrinhjavaweb.dto.respone.CustomerSearchResponse;
import com.laptrinhjavaweb.dto.respone.PaginationResponse;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.CustomerEntity_;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.SearchOperationEnum;
import com.laptrinhjavaweb.exception.NotFoundException;
import com.laptrinhjavaweb.exception.handler.ErrorMessage;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.specifications.CustomerSpecification;
import com.laptrinhjavaweb.specifications.GenericSpecification;
import com.laptrinhjavaweb.specifications.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerConveter customerConveter;

    @Override
    public CustomerDTO getCustomerById(Long id) {

        CustomerEntity customerEntity = customerRepository
                .findById(id).orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));
        return customerConveter.convertToDTO(customerEntity);
    }

    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        CustomerEntity customerEntity = customerConveter.convertToEntity(customerDTO);
        customerEntity.setStatus(SystemConstants.ACTIVE_STATUS);
        return customerConveter.convertToDTO(customerRepository.save(customerEntity));
    }

    @Override
    @Transactional
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));

        customerEntity.setFullName(customerDTO.getFullName());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setCompany(customerDTO.getCompany());
        customerEntity.setDemand(customerDTO.getDemand());
        customerEntity.setNote(customerDTO.getNote());
        CustomerEntity _customerEntity = customerRepository.save(customerEntity);
        return customerConveter.convertToDTO(_customerEntity);

    }

    @Override
    @Transactional
    public void deleteCustomers(List<Long> ids) {
        ids.stream().forEach(id -> {
            CustomerEntity customerEntity =  customerRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));
            customerEntity.setStatus(SystemConstants.NO_ACTIVE_STATUS);
            customerRepository.save(customerEntity);

        });
    }

    @Override
    public PaginationResponse<CustomerSearchResponse> searchCustomers(CustomerSearchRequest request) {

        CustomerSpecification customerSpecification = new CustomerSpecification();

        Specification<CustomerEntity> specification = Specification
                .where(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.FULL_NAME, request.getFullName(), SearchOperationEnum.CONTAINING)))
                .and(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.EMAIL, request.getEmail(), SearchOperationEnum.CONTAINING)))
                .and(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.PHONE, request.getPhone(), SearchOperationEnum.CONTAINING)))
                .and(customerSpecification.byCommon(new SearchCriteria(CustomerEntity_.STATUS, SystemConstants.ACTIVE_STATUS, SearchOperationEnum.EQUAL)))
                .and(customerSpecification.byStaffId(request.getStaffId()))
                .and(customerSpecification.orderBy(request.getSortColumnName(), request.getSortDirection()));

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getTotalPageItems());
        Page<CustomerEntity> page = customerRepository.findAll(specification, pageable);

        List<CustomerSearchResponse> responses = page
                .getContent()
                .stream()
                .map(customerConveter::convertToCustomerSearchResponse)
                .collect(Collectors.toList());

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setPage(request.getPage());
        paginationResponse.setTotalPages(page.getTotalPages());
        paginationResponse.setTotalPageItems(request.getTotalPageItems());
        paginationResponse.setTotalItems((int) page.getTotalElements());
        paginationResponse.setListResult(responses);
        return paginationResponse;
    }

    @Override
    @Transactional
    public void assignCustomer(AssignmentCustomerRequest request) {

        CustomerEntity customerEntity =  customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NotFoundException(ErrorMessageConstants.CUSTOMER_NOT_FOUND));

        List<Long> ids = request.getStaffIds();
        Long count = userRepository.countByIdIn(ids);
        if(count != ids.size()){
            throw new NotFoundException(ErrorMessageConstants.USER_NOT_FOUND);
        }else{
            customerEntity.getUsers().clear();
            customerEntity.getUsers().addAll(userRepository.findByIdIn(ids));
        }
    }
}
