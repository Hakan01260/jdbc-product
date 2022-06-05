package Service;

import com.proje.model.Brand;

import java.util.List;

public interface BrandService {

    Brand findBrand(int id);

    List<Brand> findBrands();

}
