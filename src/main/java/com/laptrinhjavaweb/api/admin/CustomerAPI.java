package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.AssignmentCustomerRequest;
import com.laptrinhjavaweb.dto.respone.StaffResponse;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO){

        return ResponseEntity.ok(customerService.updateCustomer(id, customerDTO));
    }


    @DeleteMapping
    public ResponseEntity<Void> removeCustomers(@RequestBody List<Long> ids){

        if(ids!=null && !ids.isEmpty()){
            customerService.deleteCustomers(ids);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assignment-customer")
    public ResponseEntity<Void> assignCustomer(@RequestBody AssignmentCustomerRequest request){
        customerService.assignCustomer(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{customerId}/staffs")
    public ResponseEntity<List<StaffResponse>> loadStaff(@PathVariable  Long customerId){

        return ResponseEntity.ok(userService.getStaffByCustomerId(customerId));
    }
}
