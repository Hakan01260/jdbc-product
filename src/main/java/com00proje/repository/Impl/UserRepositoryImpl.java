package com00proje.repository.Impl;

import com.proje.model.*;
import com0proje.connection.DBCon;
import com__proje.repository.UserRepository;
import com_proje.model.queries.UserQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    //"INSERT INTO user(userId, firstName,lastName, birthOfDate, userName) VALUES(?,?,?,?,?)";
    @Override
    public User saveUser(User user) {
        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(UserQuery.saveUserQuery);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setDate(4, user.getBirthOfDate());
            preparedStatement.setString(5, user.getUserName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBCon.closeConnection(connection);
        }
        return user;
    }

    //"INSERT INTO user = (userId, productId) VALUES(?,?)";
    @Override
    public boolean saveUserProduct(int userId, int productId) {
        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(UserQuery.saveUser_ProductQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBCon.closeConnection(connection);
        }
        return true;
    }

    //"UPDATE user SET firstName=?, lastName =?, birthOfDate =?, userName=?, WHERE userId =? ";
    @Override
    public User updateUser(User user) {

        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(UserQuery.updateUserQuery);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, user.getBirthOfDate());
            preparedStatement.setString(4, user.getUserName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBCon.closeConnection(connection);
        }

        return user;
    }

    //"DELETE FROM user_product WHERE userId =? ";
    //"DELETE FROM user WHERE userId =? ";
    @Override
    public boolean removeUser(int id) {

        connection = DBCon.getConnection();

        try {
            preparedStatement = connection.prepareStatement(UserQuery.deleteUser_ProductByIdQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(UserQuery.deleteUserByIdQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {

            DBCon.closeConnection(connection);
        }

        return true;
    }

    // "SELECT * FROM user WHERE userId =? ";
    @Override
    public User findUserById(int id) {

        connection = DBCon.getConnection();
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(UserQuery.findUserByIdQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date birthOfDate = resultSet.getDate("birthOfDate");
                String userName = resultSet.getString("userName");
                user = new User(userId, firstName, lastName, birthOfDate, userName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBCon.closeConnection(connection);
        }


        return user;
    }

    /*
    "SELECT * FROM user u LEFT OUTER JOİN user_product up ON(u.userId = up.user.Id) " +
                                                     "LEFT JOIN product p ON(up.productId = p.productId) " +
                                                     "LEFT JOIN category c ON(p.categoryId = c.categoryId) " +
                                                     "LEFT JOIN brand b ON(p.bradId = b.randId) " +
                                                     "WHERE u.userId=?";
     */
    @Override
    public User findUserProductsById(int id) {

        connection = DBCon.getConnection();

        User user = null;

        try {
            preparedStatement = connection.prepareStatement(UserQuery.findUserProductQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            boolean durum = true;
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {

                if (durum) {

                    int userId = resultSet.getInt("userId");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    Date birthOfDate = resultSet.getDate("birthOfDate");
                    String userName = resultSet.getString("userName");

                    user = new User(userId, firstName, lastName, birthOfDate, userName);
                    durum = false;
                }

                int productId = resultSet.getInt("productId");
                String productName = resultSet.getString("productName");
                double unitPrice = resultSet.getDouble("unitPrice");
                int avaible = resultSet.getInt("avaible");
                Date addDate = resultSet.getDate("addDate");
                Date updateDate = resultSet.getDate("updateDate");

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                int brandId = resultSet.getInt("brandId");
                String brandName = resultSet.getString("brandName");

                Category category = new Category(categoryId, categoryName);
                Brand brand = new Brand(brandId, brandName);

                Product product = new Product(productId, productName, unitPrice, avaible, addDate, updateDate, category, brand);

                products.add(product);
            }

            user.setProducts(products);

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBCon.closeConnection(connection);
        }

        return user;
    }

    //"SELECT * FROM user";

    @Override
    public List<User> findUsers() {
        connection = DBCon.getConnection();
        List<User> users = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(UserQuery.findUsersQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Date birthOfDate = resultSet.getDate("birthOfDate");
                String userName = resultSet.getString("userName");

                User user = new User(userId, firstName, lastName, birthOfDate, userName);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBCon.closeConnection(connection);
        }
        return users;
    }


}