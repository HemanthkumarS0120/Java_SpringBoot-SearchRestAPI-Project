package springboot.search_rest_api.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springboot.search_rest_api.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //
    @Query("SELECT p FROM Product p WHERE "+
            "p.name LIKE CONCAT('%',:query,'%')" +
            "OR p.description LIKE CONCAT('%',:query,'%')")
    List<Product> searchProducts(String query);

    @Query(value = "SELECT * FROM products p WHERE "+
            "p.name LIKE CONCAT('%',:query,'%')" +
            "Or p.description LIKE CONCAT('%',:query,'%')",nativeQuery = true)
    List<Product> searchProductsSQL(String query);

    //Returns the found product entry by using its name as search criteria.
    //If no product entry is found , this method returns null.
    public List<Product> findByName(String name);

    //Returns an optional which contains the found product entry by using its id as
    // search criteria. If no product entry is found , this method returns an empty optional
    Optional<Product> findById(Long id);

    //returns the found list of products entries whose title or description
    //is given as a method parameter.If no product entries is found, this method return an empty list.
    List<Product> findByNameOrDescription(String name,String description);
}
