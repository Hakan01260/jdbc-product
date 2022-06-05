package Servicee.Impl;

import Service.ProductService;
import com.proje.model.Product;
import com00proje.repository.Impl.ProductRepositoryImpl;
import com__proje.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository = new ProductRepositoryImpl();

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Boolean saveBatchProduct(List<Product> products) {
        return productRepository.saveBatchProduct(products);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public boolean removeProduct(int id) {
        return productRepository.removeProduct(id);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public List<Product> findProducts() {
        return productRepository.findProducts();
    }
}
