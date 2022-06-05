package com_proje.model.queries;

public class UserQuery {

    public static final String saveUserQuery = "INSERT INTO user(userId, firstName,lastName, birthOfDate, userName) VALUES(?,?,?,?,?)";

    public static final String saveUser_ProductQuery = "INSERT INTO user_product(userId, productId) VALUES(?,?)";

    public static final String updateUserQuery = "UPDATE user SET firstName=?, lastName =?, birthOfDate =?, userName=? WHERE userId =? ";

    public static final String deleteUser_ProductByIdQuery = "DELETE FROM user_product WHERE userId =? ";
    public static final String deleteUserByIdQuery = "DELETE FROM user WHERE userId =? ";

    public static final String findUserByIdQuery = "SELECT * FROM user WHERE userId =? ";

    public static final String findUsersQuery = "SELECT * FROM user";

    public static final String findUserProductQuery = "SELECT * FROM user u LEFT OUTER JOIN user_product up ON(u.userId = up.userId) " +
            "LEFT JOIN product p ON(up.productId = p.productId) " +
            "LEFT JOIN category c ON(p.categoryId = c.categoryId) " +
            "LEFT JOIN brand b ON(p.brandId = b.brandId) " +
            "WHERE u.userId=?";
}
