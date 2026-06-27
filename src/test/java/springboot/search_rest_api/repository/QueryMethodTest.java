package springboot.search_rest_api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.search_rest_api.entity.Product;
import springboot.search_rest_api.respository.ProductRepository;

import java.util.List;

@SpringBootTest
public class QueryMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod(){
        List<Product> product =productRepository.findByName("laptop");

    }
}
