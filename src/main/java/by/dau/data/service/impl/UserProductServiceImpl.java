package by.dau.data.service.impl;

import by.dau.data.entity.Product;
import by.dau.data.entity.User;
import by.dau.data.entity.UserProduct;
import by.dau.data.repository.UserProductRepository;
import by.dau.data.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService {

    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    public UserProduct create(UserProduct userProduct) {
        return userProductRepository.saveAndFlush(userProduct);
    }

    @Override
    public UserProduct read(long id) {
        return userProductRepository.findOne(id);
    }

    @Override
    public UserProduct update(UserProduct userProduct) {
        return userProductRepository.saveAndFlush(userProduct);
    }

    @Override
    public List<Product> getAllUserProducts(User user) {
        return userProductRepository.findAllUserProducts(user);
    }

    @Override
    public void delete(long id) {
        userProductRepository.delete(id);
    }
}
