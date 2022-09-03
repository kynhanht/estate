package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 9211022828115142019L;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
