package by.dau.data.service.impl;

import by.dau.data.entity.GameState;
import by.dau.data.entity.Match;
import by.dau.data.repository.MatchRepository;
import by.dau.data.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Match create(Match match) {
        return matchRepository.saveAndFlush(match);
    }

    @Override
    public Match read(long id) {
        return matchRepository.getOne(id);
    }

    @Override
    public Match update(Match match) {
        return matchRepository.saveAndFlush(match);
    }

    @Override
    public void delete(long id) {
        matchRepository.delete(id);
    }

    @Override
    public Match getByState(GameState gameState) {
        return matchRepository.findByGameState(gameState);
    }

}
