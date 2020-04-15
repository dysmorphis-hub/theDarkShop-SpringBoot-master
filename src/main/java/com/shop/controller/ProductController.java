package com.shop.controller;

import com.shop.model.persistence.IProductRepository;
import com.shop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping
public class ProductController {

    @Autowired
    IProductRepository productRepository;
    byte[] uploadedImageAsByte;

    //Get Request for admins to add a specific product to database
    @GetMapping("/addProduct")
    public String addProduct(Product product, Model model) {

        return "admin/addProduct";
    }

    @PostMapping("/uploadProductImage")
    public String uploadProductImage(@RequestParam("uploadedImage") MultipartFile file, Product product) throws IOException {

        if(!file.isEmpty()){
            uploadedImageAsByte = file.getBytes();
        }
        //add redirect and addFlashMessage attribute!

        return "admin/addProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(Product product){

        Product productToSave = new Product();
        productToSave.setProductImage(uploadedImageAsByte);
        productToSave.setBrand(product.getBrand());
        productToSave.setCategory(product.getCategory());
        productToSave.setColour(product.getColour());
        productToSave.setName(product.getName());
        productToSave.setStock(product.getStock());
        productToSave.setUnitCost(product.getUnitCost());
        productToSave.setWeight(product.getWeight());

        productRepository.save(productToSave);

        return "admin/savedProduct";
    }
}
