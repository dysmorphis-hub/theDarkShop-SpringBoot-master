package com.shop.model.persistence;

import com.shop.model.person.customer.Customer;
import com.shop.model.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISessionRepository extends JpaRepository<Session, Long> {

    public abstract void save (Customer customer);

    public abstract Session findByCustomerEmail (String email);


}
