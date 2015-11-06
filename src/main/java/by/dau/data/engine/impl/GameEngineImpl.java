package by.dau.data.engine.impl;

import by.dau.data.engine.GameEngine;
import by.dau.data.engine.GameThread;
import by.dau.data.entity.GameState;
import by.dau.data.entity.User;
import by.dau.data.service.GameStateService;
import by.dau.data.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GameEngineImpl implements GameEngine {

    @Resource
    GameStateService gameStateService;
    @Resource
    GameThread gameThread;
    @Resource
    UserService userService;

    private long gameStateId;

    @Override
    public long getGameStateId() {
        return gameStateId;
    }

    @Override
    public GameState create() {

        GameState gameState = new GameState();
        gameState = gameStateService.create(gameState);

        this.gameStateId = gameState.getId();

        return gameState;
    }

    @Override
    public GameState start() {
        GameState gameState = gameStateService.read(this.gameStateId);
        gameState.setState(GameState.STATE_STARTED);
        gameStateService.update(gameState);

        gameThread.start(this);

        return gameState;
    }

    @Override
    public User join(String name) {
        User result = null;
        if (name != null) {
            GameState gameState = gameStateService.read(this.gameStateId);
            result = new User(gameState, name, User.START_MONEY);
            userService.create(result);
        }
        return result;
    }

    @Override
    public GameState stop() {
        GameState gameState = gameStateService.read(this.gameStateId);
        gameState.setState(GameState.STATE_STOPPED);
        gameStateService.update(gameState);

        return gameState;
    }

    @Override
    public boolean leave(User user) {
        boolean result = false;
        if (user != null) {
            userService.delete(user.getId());
            result = true;
        }
        return result;
    }
}
