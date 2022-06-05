package com_proje.model.queries;

public class CategoryQuery {

    public static final String findCategoryByIdQuery = "SELECT * FROM category WHERE categoryId =?";

    public static final String findCategoriesQuery = "SELECT * FROM category";
}
