package com.laptrinhjavaweb.specifications;

import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.CustomerEntity_;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.entity.UserEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

public class CustomerSpecification extends GenericSpecification<CustomerEntity> {


    public Specification<CustomerEntity> byStaffId(Long staffId) {
        return (root, query, criteriaBuilder) -> {
            if (staffId == null) return criteriaBuilder.conjunction();
            Join<UserEntity, CustomerEntity> user = root.join(CustomerEntity_.USERS, JoinType.INNER);
            return criteriaBuilder.equal(user.get(UserEntity_.ID), staffId);
        };
    }
}
