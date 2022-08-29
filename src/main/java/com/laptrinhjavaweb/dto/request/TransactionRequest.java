package com.laptrinhjavaweb.dto.request;

public class TransactionRequest extends PaginationRequest {

    private String code;

    private Long customerId;

    private String note;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
