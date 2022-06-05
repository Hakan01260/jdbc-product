package com__proje.repository;

import com.proje.model.Brand;

import java.util.List;

public interface BrandRepository {

    Brand findBrand(int id);

    List<Brand> findBrands();
}
