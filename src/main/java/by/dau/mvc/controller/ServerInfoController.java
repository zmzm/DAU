package by.dau.mvc.controller;

import by.dau.data.dto.ServerStateDTO;
import by.dau.data.engine.GameEngine;
import by.dau.data.entity.GameState;
import by.dau.data.service.GameStateService;
import by.dau.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ServerInfoController {

    @Autowired
    private UserService userService;
    @Autowired
    private GameStateService gameStateService;
    @Autowired
    private GameEngine gameEngine;

    @CrossOrigin(origins = "http://localhost:8888")
    @RequestMapping(value = "/serverInfo", method = RequestMethod.GET)
    public ServerStateDTO usersCount()
    {
        GameState gameState = gameStateService.read(gameEngine.getGameStateId());
        int count = userService.getUserByState(gameState).size();
        String serverState;
        int state;
        if (gameState == null)
        {
            state = -1;
            serverState = "Game not created";
        }
        else if (gameState.getState() == 0)
        {
            state = 0;
            serverState = "Game created";
        }
        else if (gameState.getState() == 1)
        {
            state= 1;
            serverState = "Game started";
        }
        else
        {
            state = 2;
            serverState = "Game stopped";
        }
        ServerStateDTO serverStateDTO = new ServerStateDTO(serverState, count, state);
        return serverStateDTO;
    }
}
