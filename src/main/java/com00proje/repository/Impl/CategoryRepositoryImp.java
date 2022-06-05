package com00proje.repository.Impl;

import com.proje.model.Category;
import com0proje.connection.DBCon;
import com__proje.repository.CategoryRepository;
import com_proje.model.queries.CategoryQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImp implements CategoryRepository {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public Category findCategoryById(int id) {
        connection = DBCon.getConnection();
        Category category = null;

        try {
            preparedStatement = connection.prepareStatement(CategoryQuery.findCategoryByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                category = new Category(categoryId,categoryName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return category;
    }

    @Override
    public List<Category> findCategories() {
        connection = DBCon.getConnection();

        List<Category>categories = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(CategoryQuery.findCategoriesQuery);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int categoryId = resultSet.getInt("categoryId");
                String categoryName = resultSet.getString("categoryName");

                Category category = new Category(categoryId,categoryName);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return categories;
    }
}