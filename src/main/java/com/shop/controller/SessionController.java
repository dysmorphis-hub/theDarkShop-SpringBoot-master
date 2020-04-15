package com.shop.controller;

import com.shop.model.persistence.ISessionRepository;
import com.shop.model.person.customer.Customer;
import com.shop.model.session.Session;
import com.shop.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;

@Controller
@RequestMapping
public class SessionController {

    @Autowired
    private ISessionRepository sessionRepository;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/logout")
    public String logout (Customer customer, Session session) throws IOException {

        if(sessionService.checkIfCookieExists()){

            Session s = sessionRepository.findByCustomerEmail(sessionService.readEmailFromCookie());

            if(s!=null){

                sessionRepository.delete(s);
                sessionService.deleteCookie();
            }
        }
        return "redirect:/login";
    }
}
