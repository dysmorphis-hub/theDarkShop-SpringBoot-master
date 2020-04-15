package com.shop.model.persistence;

import com.shop.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAddressRepository extends JpaRepository<Address, Long> {
}
