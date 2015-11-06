package by.dau.data.service.impl;

import by.dau.data.entity.GameState;
import by.dau.data.entity.User;
import by.dau.data.repository.UserRepository;
import by.dau.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User read(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> getUserByState(GameState gameState) {
        return userRepository.getAllUsersByState(gameState);
    }

    @Override
    public User getUserByStateAndName(GameState gameState, String name) {
        return userRepository.getUsersByStateAndName(gameState, name);
    }

}
