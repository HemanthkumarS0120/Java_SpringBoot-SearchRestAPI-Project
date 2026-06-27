package springboot.search_rest_api.service.impl;

import org.springframework.stereotype.Service;
import springboot.search_rest_api.entity.Product;
import springboot.search_rest_api.respository.ProductRepository;
import springboot.search_rest_api.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchProducts(String query) {
        List<Product> products = productRepository.searchProducts(query);
        for (Product product:products) {
            System.out.println(product.toString());
        }
        return products;

    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> searchProductsById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> searchProductsByNameOrDescription(String name, String Description) {
        return productRepository.findByNameOrDescription(name, Description);
    }


}
