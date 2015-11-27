package by.dau.data.service;

import by.dau.data.entity.Product;
import by.dau.data.entity.Set;
import by.dau.data.entity.User;
import by.dau.data.entity.UserProduct;

import java.util.List;

public interface UserProductService {

    UserProduct create(UserProduct userProduct);

    UserProduct read(long id);

    UserProduct update(UserProduct userProduct);

    UserProduct findBySet(Set set);

    List<Product> getAllUserProducts(User user);

    void delete(long id);
}
