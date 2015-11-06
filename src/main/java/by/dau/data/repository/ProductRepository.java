package by.dau.data.repository;

import by.dau.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.name = :name")
    Product findByName(@Param("name") String name);

    @Query("select p.name from Product p")
    List<String> getAllProductsNames();
}
