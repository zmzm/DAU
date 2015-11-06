package by.dau.mvc.controller;

import by.dau.data.engine.GameEngine;
import by.dau.data.entity.*;
import by.dau.data.service.*;
import by.dau.data.state.CurrentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameEngine gameEngine;
    @Autowired
    MatchService matchService;
    @Autowired
    SetService setService;
    @Autowired
    UserService userService;
    @Autowired
    GameStateService gameStateService;
    @Autowired
    GameService gameService;
    @Autowired
    UserProductService userProductService;
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public long create() {
        return gameEngine.create().getId();
    }

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void start() {
        gameEngine.start();
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    public void stop() {
        gameEngine.stop();
    }

    @RequestMapping(value = "/state/{id}", method = RequestMethod.POST)
    public CurrentState state(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        Game game = gameService.getLastBySet(set);
        String productName = set.getProduct().getName();
        float price = game.getPrice();
        CurrentState currentState = new CurrentState(gameState.getId(), match.getId(), set.getId(), game.getId(), productName, price);
        return currentState;
    }

    @RequestMapping(value = "/price/{id}/{price}", method = RequestMethod.POST)
    public void newPrice(@PathVariable("id") long id, @PathVariable("price") float price) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        Game game = gameService.getLastBySet(set);
        game.setPrice(price);
        gameService.update(game);
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.POST)
    public void buy(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        Game game = gameService.getLastBySet(set);
        User user = userService.read(1);
        UserProduct userProduct = new UserProduct(user, set.getProduct(), game.getPrice());
        userProductService.create(userProduct);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public List<User> users(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        List<User> users = userService.getUserByState(gameState);
        return users;
    }

    @RequestMapping(value = "/join/{name}", method = RequestMethod.POST)
    public void join(@PathVariable("name") String name) {
        gameEngine.join(name);
    }

    @RequestMapping(value = "/beginGame/{id}", method = RequestMethod.POST)
    public void beginGame(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        List<Game> games = gameService.getAllBySet(set);
        if (games.size() != 0) {
            Game game = games.get(games.size() - 1);
            if (game.getPrice() == 0) {
                Set newSet = new Set(match, productService.getRandomProduct());
                setService.create(newSet);
                Game newGame = new Game(newSet, 55);
                gameService.create(newGame);
            } else {
                Game newGame = new Game(set, 55);
                gameService.create(newGame);
            }
        } else {
            Game newGame = new Game(set, 55);
            gameService.create(newGame);
        }
    }

    @RequestMapping(value = "/endGame/{id}", method = RequestMethod.POST)
    public void endGame(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set newSet = new Set(match, productService.getRandomProduct());
        setService.create(newSet);
        Game newGame = new Game(newSet, 55);
        gameService.create(newGame);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handlerError(Exception e) {
        e.printStackTrace();
    }
}
