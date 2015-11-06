package by.dau.data.engine.impl;

import by.dau.data.engine.GameEngine;
import by.dau.data.engine.GameThread;
import by.dau.data.entity.*;
import by.dau.data.service.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Component
@Scope("prototype")
public class GameThreadImpl extends Thread implements GameThread {

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

    private GameEngine gameEngine;

    @Override
    public void start(final GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        //todo start
        this.init();
    }

    @Override
    public void run() {
        //init();
    }

    private void init() {

        GameState gameState = gameStateService.read(gameEngine.getGameStateId());

        //todo end with error

        Match match = new Match(gameState);
        match = matchService.create(match);

        List<User> users = userService.getUserByState(gameState);
        float setCount = Math.round((float) users.size() / 2);

        //for (int i = 0; i < setCount; i++) {
            Product product = productService.getRandomProduct();

            Set set = new Set(match, product);
            //Game game = new Game(set, 55);

            setService.create(set);
            //gameService.create(game);
        //}

       /* Game game = new Game(set, price);
        game = gameService.create(game)*/
        ;

        /*while (price > 0) {
            System.out.println(game.getSett().getProduct().getName() + " " + game.getPrice());
            price -= 5;
            game.setPrice(price);
            gameService.create(game);
        }*/

        //gameEngine.stop();
    }
}
