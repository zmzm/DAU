package by.dau.test.service;

import by.dau.config.DataConfig;
import by.dau.data.engine.GameEngine;
import by.dau.data.entity.Game;
import by.dau.data.entity.GameState;
import by.dau.data.entity.Set;
import by.dau.data.entity.User;
import by.dau.data.service.*;
import by.dau.data.state.CurrentState;
import by.dau.mvc.controller.GameController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class MainTest {

    @Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    private ProductService productService;
    @Resource
    private MatchService matchService;
    @Resource
    private GameService gameService;
    @Resource
    private SetService setService;
    @Resource
    private UserService userService;
    @Resource
    private UserProductService userProductService;
    @Resource
    private GameEngine gameEngine;

    @Before
    public void setUp() throws Exception {
        em = emf.createEntityManager();
    }

    //@Test
    public void test() throws Exception {
        GameState gameState = gameEngine.create();
        //assert gameState.getState() == GameState.STATE_CREATED;

        User skas1 = gameEngine.join("skas1");
        User skas2 = gameEngine.join("skas2");
        //User skas3 = gameEngine.join("skas3");

       // assert userService.getUserByStateAndName(gameState, skas1.getName()) != null;
        //assert userService.getUserByStateAndName(gameState, skas2.getName()) != null;

        //gameEngine.leave(skas1);
        //assert userService.getUserByStateAndName(gameState, skas1.getName()) == null;
        //assert userService.getUserByStateAndName(gameState, skas2.getName()) != null;

        //gameState = gameEngine.start();
//        assert gameState.getState() == GameState.STATE_STARTED;

//        gameState = gameEngine.stop();
//        assert gameState.getState() == GameState.STATE_STOPPED;
    }

    //@Test
    public void test3() throws Exception {
        /*GameState gameState = gameEngine.create();
        gameEngine.start();
        System.out.print("sdfdsfdsf " + gameState.getId());*/

        Set set = setService.read(3);
        //System.out.println("set " + set.getId());
        Game game = gameService.getLastBySet(set);
        System.out.println("game " + game.getId() + game.getPrice());

        /*GameController gameController = new GameController();
        CurrentState currentState = gameController.state(gameState.getId());
        System.out.println(currentState);*/
    }

}
