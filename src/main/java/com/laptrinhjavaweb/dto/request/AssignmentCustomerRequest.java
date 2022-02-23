package com.laptrinhjavaweb.dto.request;

import java.io.Serializable;
import java.util.List;

public class AssignmentCustomerRequest implements Serializable {

    private List<Long> staffIds;

    private Long customerId;

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
