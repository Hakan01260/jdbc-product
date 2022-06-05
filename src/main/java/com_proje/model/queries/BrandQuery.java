package com_proje.model.queries;

public class BrandQuery {


    public static final String findBrandByIdQuery = "SELECT * FROM brand WHERE brandId = ?";

    public static final String findBrandsQuery = "SELECT * FROM brand";
}
