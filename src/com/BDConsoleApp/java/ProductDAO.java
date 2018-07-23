package com.BDConsoleApp.java;

import java.util.List;

public interface ProductDAO {
    public void insertProduct(Product product);
    public List<Product> getAllProducts();
    public Product getProduct(int productId);
    public void deleteProduct(Product product);
    public void updateProduct(Product product);
}
