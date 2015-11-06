package by.dau.data.service.impl;

import by.dau.data.entity.Match;
import by.dau.data.entity.Set;
import by.dau.data.repository.SetRepository;
import by.dau.data.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SetServiceImpl implements SetService {

    @Autowired
    private SetRepository setRepository;

    @Override
    public Set create(Set set) {
        return setRepository.save(set);
    }

    @Override
    public Set read(long id) {
        return setRepository.getOne(id);
    }

    @Override
    public Set update(Set set) {
        return setRepository.saveAndFlush(set);
    }

    @Override
    public void delete(long id) {
        setRepository.delete(id);
    }

    @Override
    public List<Set> getAllByMatch(Match match) {
        return setRepository.findByMatch(match);
    }

    @Override
    public Set getLastByMatch(Match match) {
        List<Set> data = setRepository.findByMatch(match);
       // Collections.reverse(data);
        return data.get(data.size() - 1);
    }

}
