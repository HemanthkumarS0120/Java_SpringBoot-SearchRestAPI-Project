package springboot.search_rest_api.service;

import springboot.search_rest_api.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> searchProducts(String query);

    Product createProduct(Product product);

    Optional<Product> searchProductsById(Long id);

    List<Product> searchProductsByNameOrDescription(String name , String Description);
}
