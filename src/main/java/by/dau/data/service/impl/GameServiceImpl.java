package by.dau.data.service.impl;

import by.dau.data.entity.Game;
import by.dau.data.entity.Set;
import by.dau.data.repository.GameRepository;
import by.dau.data.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public Game create(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game read(long id) {
        return gameRepository.getOne(id);
    }

    @Override
    public Game update(Game game) {
        return gameRepository.saveAndFlush(game);
    }

    @Override
    public void delete(long id) {
        gameRepository.delete(id);
    }

    @Override
    public List<Game> getAllBySet(Set set) {
        return gameRepository.findBySet(set);
    }

    @Override
    public Game getLastBySet(Set set) {
        List<Game> data = gameRepository.findBySet(set);
        //Collections.reverse(data);
        return data.get(data.size() - 1);
    }

}
