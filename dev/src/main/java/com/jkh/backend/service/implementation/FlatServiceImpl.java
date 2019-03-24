package com.jkh.backend.service.implementation;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import com.jkh.backend.repository.FlatRepository;
import com.jkh.backend.service.CounterService;
import com.jkh.backend.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FlatServiceImpl implements FlatService {
    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private CounterService counterService;

    @Override
    public void save(Flat flat) {
        flatRepository.save(flat);
    }

    @Override
    public void delete(Flat flat) {
        flatRepository.delete(flat);
    }

    @Override
    public Flat findFlatByNumber(Integer number) {
        return flatRepository.findFlatByNumber(number);
    }

    @Override
    public List<Flat> findAll() {
        return flatRepository.findAll();
    }

    @Override
    public boolean addUserToFlat(Flat flat, User user) {
        Flat savedFlat = findFlatByNumber(flat.getNumber());
        if (savedFlat == null) {
            Set<User> userSet = new HashSet<>();
            userSet.add(user);
            flat.setUserSet(userSet);
            save(flat);
            counterService.addThreeCounters(flat);
            return false;
        } else {
            user.setFlat(savedFlat);
            savedFlat.getUserSet().add(user);
            return true;
        }
    }
}
