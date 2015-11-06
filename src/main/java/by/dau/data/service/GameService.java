package by.dau.data.service;

import by.dau.data.entity.Game;
import by.dau.data.entity.Set;

import java.util.List;

public interface GameService {

    Game create(Game game);

    Game read(long id);

    Game update(Game game);

    void delete(long id);

    List<Game> getAllBySet(Set set);

    Game getLastBySet(Set set);
}
