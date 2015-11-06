package by.dau.data.engine;

import by.dau.data.entity.GameState;
import by.dau.data.entity.User;

public interface GameEngine {
    long getGameStateId();

    GameState create();

    GameState start();

    User join(String name);

    GameState stop();

    boolean leave(User user);
}
