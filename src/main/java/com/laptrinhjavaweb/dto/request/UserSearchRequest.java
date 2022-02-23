package com.laptrinhjavaweb.dto.request;

public class UserSearchRequest extends PaginationRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
