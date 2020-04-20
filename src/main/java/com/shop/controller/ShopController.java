package com.shop.controller;

import com.shop.model.address.Address;
import com.shop.model.persistence.ICustomerRepository;
import com.shop.model.persistence.ISessionRepository;
import com.shop.model.person.customer.Customer;
import com.shop.model.search.Search;
import com.shop.model.session.Session;
import com.shop.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class ShopController {

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ISessionRepository sessionRepository;
    @Autowired
    private SessionService sessionService;

    //localhost:8080
    @GetMapping({"/login", "/"})
    public String loginScreen(Customer customer, Search search) {

        return "login";
    }

    //account registration request
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String redirectOne(Customer customer, Address address) {

        return "home";
    }

    //posting registration data
    @PostMapping("/register")
    public String registerCustomer(@Valid Customer customer, Address address, Errors errors) {

        //associate address and customer
        customer.setAddress(address);
        //backend form validation
        if (errors.hasErrors()) {

            return "redirect:/home";
        } else {

            //backend check if customers email is already registered
            Customer customerExists = customerRepository.findByEmailAddress(customer.getEmailAddress());
            if (customerExists != null) {

                return "registerEmailError";
            } else {

                //save CUSTOMER WITH JPA HERE
                customerRepository.save(customer);
                return "registerConfirmation";

                //DEMO REPOSITORY method implementation HERE ...
            }
        }
    }

    @PostMapping("/lobby")  //Login: post action target is this: - /lobby from login screen
    public String lobbyLogin(Customer customer, Model model, Search search, Session session) throws IOException {

        //query customer in customer database
        Customer ifStoredCustomer = customerRepository.findByEmailAddress(customer.getEmailAddress());

        //check if already a session for this customer exists if the customer is already logged in
        List<Session> loadAllSessions = sessionRepository.findAll();
        List<Session> filteredSession = new ArrayList<>();

        if(!loadAllSessions.isEmpty()){
            filteredSession = loadAllSessions.
                    stream().
                    filter(s -> s.getCustomerEmail().equals(ifStoredCustomer.getEmailAddress())).
                    collect(Collectors.toList());
        }

        //the list of filtered Sessions is empty unless there is a login already
        if(filteredSession.isEmpty()) {

            //if the customer exists (Object !=null) a Session is created
            if (ifStoredCustomer != null){

                //add customer to current session
                session.setSessionCustomer(ifStoredCustomer);
                session.setSessionCustomerEmail(ifStoredCustomer.getEmailAddress());
                //save current session as object in the session repository
                sessionRepository.save(session);
                //write cookie object
                sessionService.writeCookieFile(session);
            }else{

                return "registerEmailError";
            }

        }else if (filteredSession.size() == 1) {

            if (sessionService.checkIfCookieExists()) {

                if (sessionService.readEmailFromCookie().equals(ifStoredCustomer.getEmailAddress())) {
                    return "redirect:/lobby";
                }
            } else {
                session.setSessionCustomer(ifStoredCustomer);
                session.setSessionCustomerEmail(ifStoredCustomer.getEmailAddress());
                sessionService.writeCookieFile(session);
            }
        }

        return "redirect:/lobby";
    }

    @GetMapping("/lobby")
    public String proceedToLobby(Customer customer, Model model, Search search) throws IOException {

        String email = "";
        //retrieve from cookie here
        try{
             email = sessionService.readEmailFromCookie();
        }catch (FileNotFoundException e){

            return "redirect:/login";
        }

        if (!sessionRepository.findAll().isEmpty()) {

            Session session = sessionRepository.findByCustomerEmail(email);
            //look for session/email (server) which is written in the cookie file (client)
            //check if the respective found session is also containing the correct email
            Function<Session, String> ifEmailLoggedIn = SessionService::apply;
            //if true then display the logged in e-mail
            model.addAttribute("emailAddress", ifEmailLoggedIn.apply(session));

            return "lobby";

        }else {

            return "redirect:/login";
        }
    }

}

