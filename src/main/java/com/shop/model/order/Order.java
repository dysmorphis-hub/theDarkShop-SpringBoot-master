package com.shop.model.order;

import com.shop.model.person.customer.Customer;
import com.shop.model.product.Product;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Order {

    private Customer customer;
    private ArrayList<Product> products;
    private String paymentMethod;
    //not sure what is the best data type for customer ID e.g. uuid
    private UUID uuid;
    private double unitCost;
    private String orderId;
    private Date created;

    public Order(Customer customer, Product product, String paymentMethod) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.products.add(product);
        this.paymentMethod = paymentMethod;
        this.uuid = UUID.randomUUID();
        this.orderId = uuid.toString();
        this.created = Date.from(Instant.now());
    }

    public double getTotalAmountBill (String orderId){
        double overall = 0.0;
        if (this.orderId.equals(orderId)){
            for (Product p : this.products){
                overall += p.getUnitCost();
            }
            return overall;
        }else {
            return -1.0;
        }

    }

    public void addProductToOrder (Product product){
        this.products.add(product);
    }
    public void removeProductFromOrder (Product product){
        this.products.remove(product);
    }

    //use INTERFACE - currently deleted
    public void setPaymentMethod (String paymentMethod){
        this.paymentMethod = paymentMethod;

    }

    public ArrayList<Product> getAllOrderItems (String orderId) {
        return this.products;
    }


    @Override
    public String toString() {
        return "ORDER ID: " + this.getorderId() +"\n" + this.customer.toString() + "\nHat folgende Items bestellt: " +
                this.getAllOrderItems(this.orderId) + "\n\n mit dem Gesamtpreis von: € " + this.getTotalAmountBill(this.orderId) +
                "\n\n PAYMENT METHOD: " + this.paymentMethod + "\n ORDER CREATED: " + this.getCreated();
    }

    private String getorderId() {
        return this.orderId;
    }
    private Date getCreated(){
        return this.created;
    }
}
