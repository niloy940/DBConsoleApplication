package com.BDConsoleApp.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDAOMySQLImplemantation implements ProductDAO {
    private PreparedStatement listProductsStatement;
    private PreparedStatement insertProductsStatement;
    private PreparedStatement getProductStatement;
    private PreparedStatement deleteProductStatement;

    private Map<String, String> queries;

    public ProductDAOMySQLImplemantation(){
        queries = new QueryReader().getQueries();

        Connection connection = ConnectionSingleton.getConnection();
        try {
            listProductsStatement = connection.prepareStatement(queries.get("LIST_PRODUCTS"));
            insertProductsStatement = connection.prepareStatement(queries.get("ADD_PRODUCT"));
            getProductStatement = connection.prepareStatement(queries.get("GET_PRODUCT"));
            deleteProductStatement = connection.prepareStatement(queries.get("DELETE_PRODUCT"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProduct(Product product) {
        try {
            /*Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("insert into product values(%d, '%s', %f);",
                    product.getProductId(), product.getProductName(), product.getUnitPrice());*/

            insertProductsStatement.setInt(1, product.getProductId());
            insertProductsStatement.setString(2, product.getProductName());
            insertProductsStatement.setDouble(3, product.getUnitPrice());

            insertProductsStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            ResultSet resultSet = listProductsStatement.executeQuery();
            while (resultSet.next()){
                int productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                double unitPrice = resultSet.getDouble("price");

                Product product = new Product(productId, productName, unitPrice);
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProduct(int productId) {
        Product product = null;
        try {
            /*Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM product WHERE id = %d;", productId);*/

            getProductStatement.setInt(1, productId);
            ResultSet resultSet = getProductStatement.executeQuery();
            while (resultSet.next()) {
                productId = resultSet.getInt("id");
                String productName = resultSet.getString("name");
                double unitPrice = resultSet.getDouble("price");

                product = new Product(productId, productName, unitPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void deleteProduct(Product product) {
        try {
            deleteProductStatement.setInt(1, product.getProductId());
            deleteProductStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();

            String query = String.format("UPDATE product SET name = 'AnyName', price = 2000 WHERE id = %d;",
                    product.getProductId());

            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
