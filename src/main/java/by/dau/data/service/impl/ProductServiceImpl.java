package by.dau.data.service.impl;

import by.dau.data.entity.Product;
import by.dau.data.repository.ProductRepository;
import by.dau.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product read(long id) {
        return productRepository.getOne(id);
    }

    @Override
    public Product update(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getRandomProduct() {
        Random random = new Random();
        List<Product> products = getAll();
        int rnd = random.nextInt(products.size());
        Product product = products.get(rnd);
        return product;
    }

}
