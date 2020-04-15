package com.shop.model.persistence;/*

package com.example.demo.model.persistence;

import com.example.demo.model.person.customer.Customer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PersistData {


    public static void persist (Customer customer){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/theDarkShop?user=root");
            Statement statement = con.createStatement();
            String queryString = "select * from customer where emailAddress = '"+ customer.getCustomerEmailAddress() +"'";
            ResultSet resultSet = (ResultSet) statement.executeQuery(queryString);

            if (resultSet.next())
            {
                System.out.println("LOG: CUSTOMER ist bereits vorhanden: " + customer.getCustomerEmailAddress());
            }else {

                //Catch Customer for insert into DB
                String sqlString = "INSERT INTO customer (firstName, secondName, street, zipcode, city, state, age, emailAddress, password) VALUES (" +
                        "'" + customer.getFirstName() + "'" +
                        "," + "'" + customer.getSecondName() + "'" +
                        "," + "'" + customer.getAddress().getStreet() + "'" +
                        "," + "'" + customer.getAddress().getZipCode() + "'" +
                        "," + "'" + customer.getAddress().getCity() + "'" +
                        "," + "'" + customer.getAddress().getState() + "'" +
                        "," + "'" + customer.getAge() + "'" +
                        "," + "'" + customer.getCustomerEmailAddress() + "'" +
                        "," + "'" + customer.getCustomerPassword() + "'" + ")";

                statement.executeUpdate(sqlString);
                System.out.println("LOG: Query update customer erfolgreich");
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }

    public static void persist (Product product){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/theDarkShop?user=root");
            Statement statement = con.createStatement();
            String queryString = "select * from product where productId = '"+ product.getProductId() +"'";
            ResultSet resultSet = (ResultSet) statement.executeQuery(queryString);

            if (resultSet.next())
            {
                System.out.println("LOG: PRODUKT in DATENBANK bereits REGISTRIERT " + product.getProductId() + "\n" +
                        "Aktueller LAGERSTAND: " + product.getStock() + "\n Einzelpreis: " + product.getSinglePrice());
            }else {

                //Catch product for insert into DB
                String sqlString = "INSERT INTO products (category, productName,brand,colour,unitCost,weight,productId,stock) VALUES (" +
                        "'" + product.getType() + "'" +
                        "," + "'" + product.getName() + "'" +
                        "," + "'" + product.getBrand() + "'" +
                        "," + "'" + product.getColour() + "'" +
                        "," + "'" + product.getSinglePrice() + "'" +
                        "," + "'" + product.getWeight() + "'" +
                        "," + "'" + product.getProductId() + "'" +
                        "," + "'" + product.getStock()+ "'" + ")";

                statement.executeUpdate(sqlString);
                System.out.println("LOG: Query update product erfolgreich");
            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }

    }

    public static boolean queryCustomerMailExists (String query){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/theDarkShop?user=root");
            Statement statement = con.createStatement();
            String queryString = "select * from customer where emailAddress = '"+ query +"'";
            ResultSet resultSet = statement.executeQuery(queryString);

            if (resultSet.next())
            {
                return true;
            }else {

                return false;

            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }
        return true;
    }

    public static String queryCustomerMailPassword (String query){
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/theDarkShop?user=root");
            Statement statement = con.createStatement();
            String queryString = "select * from customer where emailAddress = '"+ query +"'";
            ResultSet resultSet = statement.executeQuery(queryString);


            if (resultSet.next())
            {
                password = resultSet.getString("password");

            }else {

                return null;

            }

        }catch (Exception e){

            System.out.println(e.getMessage());
        }
        return password;
    }
}
*/
