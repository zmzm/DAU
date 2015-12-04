package by.dau.mvc.controller;

import by.dau.data.engine.GameEngine;
import by.dau.data.entity.*;
import by.dau.data.service.*;
import by.dau.data.state.CurrentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        CurrentState currentState = null;
        if (gameState == null) {
            return currentState;
        }
        Match match = matchService.getByState(gameState);
        if (match == null) {
            return currentState;
        }
        Set set = setService.getLastByMatch(match);
        Game game = gameService.getLastBySet(set);
        String productName = set.getProduct().getName();
        float price = game.getPrice();
        currentState = new CurrentState(gameState.getId(), match.getId(), set.getId(), game.getId(), productName, price);
        return currentState;
    }

    /*@RequestMapping(value = "/price/{id}/{price}", method = RequestMethod.POST)
    public void newPrice(@PathVariable("id") long id, @PathVariable("price") float price) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        Game game = new Game(set, price);
        gameService.create(game);
    }*/

    @RequestMapping(value = "/buy/{id}/{id1}", method = RequestMethod.POST)
    public String buy(@PathVariable("id") long id, @PathVariable("id1") long id1) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        List<Game> games = gameService.getAllBySet(set);
        Game lastGame = games.get(games.size() - 1);
        float price = lastGame.getPrice();
        User user = userService.read(id1);
        if (userProductService.findBySet(set) != null) {
            return "You are late.";
        }
        if (games.size() > 1 && (games.get(games.size() - 2).getPrice() - lastGame.getPrice()) == set.getJoker()) {
            UserProduct userProduct = new UserProduct(user, set.getProduct(), set, 0);
            userProductService.create(userProduct);
            return "Product bought with joker";
        }
        if (user.getMoney() < price) {
            return "No money.";
        }
        user.setMoney(user.getMoney() - price);
        userService.update(user);
        UserProduct userProduct = new UserProduct(user, set.getProduct(), set, lastGame.getPrice());
        userProductService.create(userProduct);
        return "Product bought.";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public List<User> users(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        List<User> users = userService.getUserByState(gameState);
        return users;
    }

    @RequestMapping(value = "/join/{name}", method = RequestMethod.POST)
    public User join(@PathVariable("name") String name) {
        return gameEngine.join(name);
    }

    /*@RequestMapping(value = "/beginGame/{id}", method = RequestMethod.POST)
    public void beginGame(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        List<Game> games = gameService.getAllBySet(set);
        if (games.size() != 0) {
            Game game = games.get(games.size() - 1);
            if (game.getPrice() == 0 || userProductService.findBySet(set) != null) {
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
    }*/

    /*@RequestMapping(value = "/endGame/{id}", method = RequestMethod.POST)
    public void endGame(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set newSet = new Set(match, productService.getRandomProduct());
        setService.create(newSet);
        Game newGame = new Game(newSet, 55);
        gameService.create(newGame);
    }*/

    @RequestMapping(value = "/joker/{id}/{joker}/{userId}", method = RequestMethod.POST)
    public String joker(@PathVariable("id") long id, @PathVariable("joker") int joker, @PathVariable("userId") long userId) {
        Set set = setService.read(id);
        set.setJoker(joker);
        setService.update(set);
        User user = userService.read(userId);
        if (user.getMoney() < 15) {
            return "No money.";
        }
        user.setMoney(user.getMoney() - 15);
        userService.update(user);
        return "Joker is bought.";
    }

    @RequestMapping(value = "/userProduct/{id}", method = RequestMethod.POST)
    public UserProduct userProduct(@PathVariable("id") long id) {
        GameState gameState = gameStateService.read(id);
        Match match = matchService.getByState(gameState);
        Set set = setService.getLastByMatch(match);
        return userProductService.findBySet(set);
    }
}
