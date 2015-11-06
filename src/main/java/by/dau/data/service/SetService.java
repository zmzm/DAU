package by.dau.data.service;

import by.dau.data.entity.Match;
import by.dau.data.entity.Set;

import java.util.List;

public interface SetService {

    Set create(Set set);

    Set read(long id);

    Set update(Set set);

    void delete(long id);

    List<Set> getAllByMatch(Match match);

    Set getLastByMatch(Match match);
}
