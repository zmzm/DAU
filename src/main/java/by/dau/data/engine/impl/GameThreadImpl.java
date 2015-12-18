package by.dau.data.engine.impl;

import by.dau.data.engine.GameEngine;
import by.dau.data.engine.GameThread;
import by.dau.data.entity.*;
import by.dau.data.service.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

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
    @Resource
    UserProductService userProductService;

    private Match match;
    private GameState gameState;
    private Random rnd = new Random();

    @Override
    public void start(final GameEngine gameEngine) {
        this.gameState = gameStateService.read(gameEngine.getGameStateId());

        this.match = new Match(this.gameState);
        this.match = matchService.create(this.match);
        Product product = productService.getRandomProduct();
        Set set = new Set(this.match, product);
        setService.create(set);
        Game game = new Game(set, 55);
        gameService.create(game);

    }

    @Scheduled(cron = "${scheduling.cron.timer}")
    public void run() {
        if (gameStateService.read(this.gameState.getId()).getState() == GameState.STATE_STARTED) {
            int priceStep = rnd.nextInt(10);
            beginGame(priceStep);
        }
    }

    public void beginGame(int step) {
        Set set = setService.getLastByMatch(this.match);
        List<Game> games = gameService.getAllBySet(set);
        Game newGame;
        if (games.size() != 0) {
            Game game = games.get(games.size() - 1);
            float price = game.getPrice();
            if (price == 0 || userProductService.findBySet(set) != null) {
                Set newSet = new Set(match, productService.getRandomProduct());
                setService.create(newSet);
                newGame = new Game(newSet, 55);
                gameService.create(newGame);
            } else {
                float newPrice = price - step;
                if(newPrice > 0){
                    newGame = new Game(set, newPrice);
                }
                else{
                    newGame = new Game(set, 0);
                }
                gameService.create(newGame);
            }
        } else {
            newGame = new Game(set, 55);
            gameService.create(newGame);
        }
    }
}