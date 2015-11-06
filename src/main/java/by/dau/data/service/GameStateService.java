package by.dau.data.service;

import by.dau.data.entity.GameState;

public interface GameStateService {

    GameState create(GameState gameState);

    GameState read(long id);

    GameState update(GameState gameState);

    void delete(long id);
}
