package by.dau.data.engine.impl;

import by.dau.data.engine.GameEngine;
import by.dau.data.engine.GameThread;
import by.dau.data.entity.*;
import by.dau.data.service.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GameThreadImpl implements GameThread {

    @Resource
    GameStateService gameStateService;
    @Resource
    MatchService matchService;
    @Resource
    SetService setService;
    @Resource
    GameService gameService;
    @Resource
    ProductService productService;
    @Resource
    UserService userService;

    GameEngine gameEngine;
    private Match match;

    @Override
    public void start(final GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        GameState gameState = gameStateService.read(gameEngine.getGameStateId());

        this.match = new Match(gameState);
        this.match = matchService.create(this.match);

        this.run();
    }

    @Scheduled(cron = "${scheduling.cron.timer}")
    public void run() {
        Product product = productService.getRandomProduct();
        Set set = new Set(this.match, product);
        Game game = new Game(set, 55);

        setService.create(set);
        gameService.create(game);
    }
}
