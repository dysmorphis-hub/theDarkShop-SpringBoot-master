package com.shop.model.person.customer;

import com.shop.model.address.Address;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    @NotNull
    private String firstName, secondName;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address = null;

    @NotBlank(message = "Email is required")
    private String emailAddress = null;

    @NotBlank
    @Size(min = 3, message = "password has to be at least 3 characters long")
    private String password = null;

    @OneToOne()
    public Address getAddress(){

        return this.address;
    }

    public Customer(String firstName, String secondName, Address address, int age, String emailAddress, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
        this.age = age;
        this.emailAddress = emailAddress;
        this.password = password;
    }
    public Customer(){
    }

}
