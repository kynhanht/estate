package com.laptrinhjavaweb.builder;

public class CustomerSearchBuilder {

    private String fullName;

    private String phone;

    private String email;

    private Long staffId;

    private Integer status;


    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getStaffId() {
        return staffId;
    }

    public Integer getStatus() {
        return status;
    }

    private CustomerSearchBuilder(Builder builder) {
        this.fullName = builder.fullName;
        this.phone = builder.phone;
        this.email = builder.email;
        this.staffId = builder.staffId;
        this.status = builder.status;
    }

    public static class Builder {

        private String fullName;

        private String phone;

        private String email;

        private Long staffId;

        private Integer status;


        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public CustomerSearchBuilder build() {
            return new CustomerSearchBuilder(this);
        }
    }
}
