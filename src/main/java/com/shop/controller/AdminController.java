package com.shop.controller;

import com.shop.model.persistence.ICustomerRepository;
import com.shop.model.persistence.ISessionRepository;
import com.shop.model.person.customer.Customer;
import com.shop.model.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class AdminController {

    @Autowired
    private ISessionRepository sessionRepository;
    @Autowired
    private ICustomerRepository customerRepository;

    //Get Request for clearing all customers and current active sessions out of the database
    @GetMapping("/clearSessionsAndCustomers")
    public String clearSessionsAndCustomers(Customer customer, Search search) {

        sessionRepository.deleteAll();
        customerRepository.deleteAll();

        return "login";
    }

    //Get Request for clearing all current active sessions out of the database
    @GetMapping("/clearSessions")
    public String clearSessions(Customer customer, Search search) {

        sessionRepository.deleteAll();

        return "login";
    }

    //Get Request for clearing all customers out of the database
    @GetMapping("/clearCustomers")
    public String clearCustomers(Customer customer, Search search) {

        customerRepository.deleteAll();

        return "login";
    }

    @GetMapping("/listAll")
    public String listAllCustomers(Customer customer, Model model, Search search) {

        List<Customer> customerList = customerRepository.findAll();
        model.addAttribute("customers", customerList);

        return "listAll";
    }

}
