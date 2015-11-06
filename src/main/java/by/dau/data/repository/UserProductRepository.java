package by.dau.data.repository;

import by.dau.data.entity.Product;
import by.dau.data.entity.User;
import by.dau.data.entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
    @Query("select p.product from UserProduct p where p.user = :user")
    List<Product> findAllUserProducts(@Param("user") User user);
}
