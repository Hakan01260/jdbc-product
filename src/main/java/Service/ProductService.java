package Service;

import com.proje.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Boolean saveBatchProduct(List<Product> products);

    Product updateProduct(Product product);

    boolean removeProduct(int id);

    Product findProductById(int id);

    List<Product> findProducts();
}
