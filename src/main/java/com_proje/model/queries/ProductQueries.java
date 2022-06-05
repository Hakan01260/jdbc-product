package com_proje.model.queries;

public class ProductQueries {

    public static final String saveProductQuery = "INSERT INTO product(productId, productName, unitPrice," +
            " avaible, addDate, updateDate, categoryId, brandId) VALUES (?,?,?,?,?,?,?,?)";

    public static final String updateProductQuery = "UPDATE product SET productName = ?, unitPrice =?, avaible =?, " +
            "updateDate =? , categoryId=?, brandId=? WHERE productId =? ";

    public static final String deleteUser_ProductQuery = "DELETE FROM user_product WHERE productId = ?";
    public static final String deleteProductQuery = "DELETE FROM product WHERE productId = ? ";

    public static final String findProductByIdQuery = "SELECT * FROM product p LEFT JOIN category c ON(p.categoryId = c.categoryId) LEFT JOIN brand b on(p.brandId = b.brandId) WHERE productId=? ";

    public static final String findProductsQuery = "SELECT * FROM product p LEFT JOIN category c ON(p.categoryId = c.categoryId)" +
            " LEFT JOIN brand b ON(p.brandId = b.brandId)";
}
