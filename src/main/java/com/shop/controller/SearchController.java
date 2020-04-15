package com.shop.controller;

import com.shop.model.persistence.IProductRepository;
import com.shop.model.persistence.ISessionRepository;
import com.shop.model.product.Product;
import com.shop.model.search.Search;
import com.shop.model.session.Session;
import com.shop.services.SessionService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class SearchController {

    @Autowired
    IProductRepository productRepository;
    @Autowired
    ISessionRepository sessionRepository;
    List<Product> allProducts = new ArrayList<>();
    String searchString = "";

    @PostMapping("/search")
    public String searchProducts(Search search){

        searchString = search.getSearchValue();
        return "redirect:/search";
    }

    @GetMapping("/search")
    public String searchProducts(Model model, Search search) throws IOException {

        allProducts = productRepository.findAll();
        List<Product> searchResultProductList;
        //Search for respective Products with Predicate
        searchResultProductList = allProducts.stream().filter(new Predicate<Product>() {
            @Override
            public boolean test(Product product) {

                return product.getName().contains(searchString) ||
                        product.getBrand().contains(searchString) ||
                        product.getCategory().contains(searchString) ||
                        product.getColour().contains(searchString);
            }
        }).collect(Collectors.toList());

        model.addAttribute("searchResult", searchResultProductList);


        String email = new SessionService().readEmailFromCookie();
        if (!sessionRepository.findAll().isEmpty()) {

            Session session = sessionRepository.findByCustomerEmail(email);  //look for session/email (server) which is written in the cookie file (client)
            //check if the respective found session is also containing the correct email
            Function<Session, String> ifEmailLoggedIn = SessionService::apply;
            //if true then display the logged in e-mail
            model.addAttribute("emailAddress", ifEmailLoggedIn.apply(session));
        }

        return "search";
    }
}
