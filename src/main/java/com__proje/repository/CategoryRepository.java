package com__proje.repository;

import com.proje.model.Category;

import java.util.List;

public interface CategoryRepository {

    Category findCategoryById(int id);

    List<Category> findCategories();
}
