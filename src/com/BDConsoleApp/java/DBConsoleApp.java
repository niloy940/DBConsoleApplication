package com.BDConsoleApp.java;

import java.util.List;

public class DBConsoleApp {
    DBConsoleApp(){
        ProductDAO productDAO = new ProductDAOMySQLImplemantation();

        //insert product
        /*Product product = new Product(3,"Walton Mobile", 15000);
        System.out.println(product);
        productDAO.insertProduct(product);*/

        //list products
        List<Product> productList = productDAO.getAllProducts();
        for (Product product : productList)
            System.out.println(product);

        //get product
        /*Product product = productDAO.getProduct(2);
        System.out.println(product);*/

        //delete product
        /*Product product = productDAO.getProduct(3);
        productDAO.deleteProduct(product);*/

        /*Product product = productDAO.getProduct(22);
        productDAO.updateProduct(product);*/
    }
}
