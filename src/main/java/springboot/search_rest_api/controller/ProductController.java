package springboot.search_rest_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.search_rest_api.entity.Product;
import springboot.search_rest_api.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

   /* @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProducts(query));
    }*/

    @GetMapping("/search/{id}")
    public ResponseEntity<Optional<Product>> searchProductsById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(productService.searchProductsById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByNameOrDescription(@RequestParam("nameOrDescription") String nameOrDescription){
        return ResponseEntity.ok(productService.searchProductsByNameOrDescription(nameOrDescription,nameOrDescription));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }


}
