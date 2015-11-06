package by.dau.data.service;

import by.dau.data.entity.GameState;
import by.dau.data.entity.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User read(long id);

    User update(User user);

    void delete(long id);

    List<User> getUserByState(GameState gameState);

    User getUserByStateAndName(GameState gameState, String name);
}
