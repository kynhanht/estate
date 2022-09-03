package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> searchCustomers(CustomerSearchBuilder builder) {

        StringBuilder sql = new StringBuilder("SELECT c FROM CustomerEntity AS c \n");
        sql.append("WHERE 1=1 \n");

        if (StringUtils.isNotBlank(builder.getFullName())) {
            sql.append("AND c.fullName like '%" + builder.getFullName() + "%' \n");
        }
        if (StringUtils.isNotBlank(builder.getPhone())) {
            sql.append("AND c.phone like '%" + builder.getPhone() + "%' \n");
        }
        if (StringUtils.isNotBlank(builder.getEmail())) {
            sql.append("AND c.email like '%" + builder.getEmail() + "%' \n");
        }
        if (builder.getStaffId() != null) {
            sql.append("AND EXISTS (SELECT u FROM c.users AS u WHERE u.id = " + builder.getStaffId() + ")\n");
        }
        sql.append("AND c.status = " + builder.getStatus());

        TypedQuery<CustomerEntity> query = entityManager.createQuery(sql.toString(), CustomerEntity.class);

        return query.getResultList();
    }
}
