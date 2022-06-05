package Servicee.Impl;

import Service.CategoryService;
import com.proje.model.Category;
import com00proje.repository.Impl.CategoryRepositoryImp;
import com__proje.repository.CategoryRepository;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository = new CategoryRepositoryImp();


    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    @Override
    public List<Category> findCategories() {
        return categoryRepository.findCategories();
    }
}
