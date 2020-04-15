package com.shop.model.persistence;

import com.shop.model.person.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    public abstract Customer findByEmailAddress (String email);

}
