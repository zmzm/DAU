package by.dau.data.service;

import by.dau.data.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    Product read(long id);

    Product update(Product product);

    void delete(long id);

    Product getByName(String name);

    List<Product> getAll();

    Product getRandomProduct();

}
