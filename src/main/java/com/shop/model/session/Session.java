package com.shop.model.session;

import com.shop.model.person.customer.Customer;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToOne(fetch = FetchType.EAGER)
    private Customer sessionCustomer;
    private String customerEmail;

    public void setSessionCustomerEmail(String sessionEmail){

        this.customerEmail = sessionEmail;
    }
}
