package com00proje.repository.Impl;

import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com0proje.connection.DBCon;
import com__proje.repository.ProductRepository;
import com_proje.model.queries.ProductQueries;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImpl implements ProductRepository {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet =null;

    //"INSERT INTO product(productId, productName, unitPrice, avaible, addDate, updateDate, categoryId, brandId) VALUES (?,?,?,?,?,?,?,?)";
    @Override
    public Product saveProduct(Product product) {
        connection = DBCon.getConnection();

        LocalDateTime localDateTime = LocalDateTime.now();

        try {
            preparedStatement = connection.prepareStatement(ProductQueries.saveProductQuery);
            preparedStatement.setInt(1,product.getProductId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setDouble(3,product.getUnitPrice());
            preparedStatement.setInt(4, product.getAvaible());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
            preparedStatement.setTimestamp(6, null);
            preparedStatement.setInt(7,product.getCategory().getCategoryId());
            preparedStatement.setInt(8,product.getBrand().getBrandId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return product;
    }

    @Override
    public Boolean saveBatchProduct(List<Product> products) {

        connection = DBCon.getConnection();

        LocalDateTime localDateTime = LocalDateTime.now();

        try {
            preparedStatement = connection.prepareStatement(ProductQueries.saveProductQuery);
            for (Product product : products){
                preparedStatement.setInt(1,product.getProductId());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setDouble(3,product.getUnitPrice());
                preparedStatement.setInt(4, product.getAvaible());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(localDateTime));
                preparedStatement.setTimestamp(6, null);
                preparedStatement.setInt(7,product.getCategory().getCategoryId());
                preparedStatement.setInt(8,product.getBrand().getBrandId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return true;
    }

    @Override
    public Product updateProduct(Product product) {
        connection = DBCon.getConnection();

        LocalDateTime localDateTime = LocalDateTime.now();

        try {
            preparedStatement = connection.prepareStatement(ProductQueries.updateProductQuery);

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2,product.getUnitPrice());
            preparedStatement.setInt(3, product.getAvaible());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            preparedStatement.setInt(5,product.getCategory().getCategoryId());
            preparedStatement.setInt(6,product.getBrand().getBrandId());
            preparedStatement.setInt(7,product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return product;
    }

    @Override
    public boolean removeProduct(int id) {
        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(ProductQueries.deleteUser_ProductQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ProductQueries.deleteProductQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return true;
    }

    @Override
    public Product findProductById(int id) {
        connection = DBCon.getConnection();
        Product product= null;
        try {
            preparedStatement = connection.prepareStatement(ProductQueries.findProductByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                product = getProduct(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> findProducts() {
        connection = DBCon.getConnection();

        List<Product> products = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(ProductQueries.findProductsQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Product product = getProduct(resultSet);
                products.add(product);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return products;
    }
    private Product getProduct(ResultSet resultSet){

        try {
            int productId = resultSet.getInt("productId");
            String productName = resultSet.getString("productName");
            double unitPrice = resultSet.getDouble("unitPrice");
            int available = resultSet.getInt("avaible");
            Date addDate = resultSet.getDate("addDate");
            Date updateDate = resultSet.getDate("updateDate");

            int categoryId = resultSet.getInt("categoryId");
            String categoryName = resultSet.getString("categoryName");

            int brandId = resultSet.getInt("brandId");
            String brandName = resultSet.getString("brandName");

            Category category = new Category(categoryId,categoryName);
            Brand brand = new Brand(brandId,brandName);

            return new Product(productId, productName, unitPrice, available,addDate,updateDate,category,brand);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
