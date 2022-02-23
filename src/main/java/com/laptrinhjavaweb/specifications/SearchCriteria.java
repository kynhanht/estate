package com.laptrinhjavaweb.specifications;

import com.laptrinhjavaweb.enums.SearchOperationEnum;

public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperationEnum operation;

    public SearchCriteria(String key, Object value, SearchOperationEnum operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchOperationEnum getOperation() {
        return operation;
    }

    public void setOperation(SearchOperationEnum operation) {
        this.operation = operation;
    }
}
