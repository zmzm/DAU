package by.dau.data.service;

import by.dau.data.entity.GameState;
import by.dau.data.entity.Match;

public interface MatchService {

    Match create(Match match);

    Match read(long id);

    Match update(Match match);

    void delete(long id);

    Match getByState(GameState gameState);
}
