package Servicee.Impl;

import Service.BrandService;
import com.proje.model.Brand;
import com00proje.repository.Impl.BrandRepositoryImpl;
import com__proje.repository.BrandRepository;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository = new BrandRepositoryImpl();

    @Override
    public Brand findBrand(int id) {
        return brandRepository.findBrand(id);
    }

    @Override
    public List<Brand> findBrands() {
        return brandRepository.findBrands();
    }
}
