package com00proje.repository.Impl;

import com.proje.model.Brand;
import com0proje.connection.DBCon;
import com__proje.repository.BrandRepository;
import com_proje.model.queries.BrandQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandRepositoryImpl implements BrandRepository {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    @Override
    public Brand findBrand(int id) {
        connection = DBCon.getConnection();
        Brand brand = null;

        try {
            preparedStatement = connection.prepareStatement(BrandQuery.findBrandByIdQuery);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){

                int brandId = resultSet.getInt("brandId");
                String brandName = resultSet.getString("brandName");

                brand = new Brand(brandId,brandName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return brand;
    }

    @Override
    public List<Brand> findBrands() {
        connection = DBCon.getConnection();

        List<Brand>brands = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(BrandQuery.findBrandsQuery);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int brandId = resultSet.getInt("brandId");
                String brandName = resultSet.getString("brandName");

                Brand brand = new Brand(brandId,brandName);
                brands.add(brand);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            DBCon.closeConnection(connection);
        }
        return brands;
    }
}
