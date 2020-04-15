package com.shop.model;/*
package com.example.demo.model;

import com.example.demo.model.order.Order;
import com.example.demo.model.person.customer.Customer;
import com.model.order.Order;
import com.model.persistence.PersistData;
import com.model.person.customer.Customer;
import com.model.product.Product;

import java.util.ArrayList;

public class BusinessHandler {

    // This class is responsible for the checkout process.
    // It handles all orders. Adds/removes customers.
    // It will contain the list of orders, list of customers, checkout menu

    private ArrayList<Order> allOrders = new ArrayList<>();

    public ArrayList<Order> showAllOrders (){

        return this.allOrders;
    }

    //will be replaced through database
    public void addOrder (Order order){
        this.allOrders.add(order);
    }


    //save a customer to database
    public void saveToDataBase(Customer customer){

        PersistData.persist(customer);

    }
    public void saveToDataBase(Product product){

        PersistData.persist(product);
    }

    public boolean searchEmailInDataBase(String query){

        return PersistData.queryCustomerMailExists(query);
    }
    public String queryPasswordInDataBase (String query){

        return PersistData.queryCustomerMailPassword(query);
    }

}
*/
