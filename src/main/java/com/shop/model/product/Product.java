package com.shop.model.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Base64;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name, brand, colour;
    private double unitCost, weight;
    private String category;
    private int stock;

    @Lob
    private byte[] productImage;

    public String getProductImageAsBase64String(){

        return Base64.getEncoder().encodeToString(this.productImage);
    }

    public void setProductImage(byte[] productImage){

        this.productImage = productImage;
    }

    public Product(String category, String name, String brand, String colour, double weight,
                   int stock, double unitCost, byte [] productImage) {
        this.category = category;
        this.name = name;
        this.brand = brand;
        this.colour = colour;
        this.unitCost = unitCost;
        this.weight = weight;
        this.stock = stock;
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "\n PRODUCT INFORMATION: " +
                "\n name: " + this.name +
                "\n brand: " + this.brand +
                "\n colour: " + this.colour +
                "\n price: " + this.unitCost +
                "\n weight: " + this.weight + "\n";
    }
}
