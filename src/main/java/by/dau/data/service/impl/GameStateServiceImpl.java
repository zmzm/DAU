package by.dau.data.service.impl;


import by.dau.data.entity.GameState;
import by.dau.data.repository.GameStateRepository;
import by.dau.data.service.GameStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameStateServiceImpl implements GameStateService {
    @Autowired
    private GameStateRepository gameStateRepository;


    @Override
    public GameState create(GameState gameState) {
        return gameStateRepository.saveAndFlush(gameState);
    }

    @Override
    public GameState read(long id) {
        return gameStateRepository.findOne(id);
    }

    @Override
    public GameState update(GameState gameState) {
        return gameStateRepository.saveAndFlush(gameState);
    }

    @Override
    public void delete(long id) {
        gameStateRepository.delete(id);
    }
}
