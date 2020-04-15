package com.shop.model.address;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String street, zipCode, city, state;

    public Address (String street, String zipCode, String city, String state) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public Address() {

    }

    @Override
    public String toString() {
        return this.street + "\n" + this.zipCode + " " + this.city + "\n" + this.state;

    }

}
