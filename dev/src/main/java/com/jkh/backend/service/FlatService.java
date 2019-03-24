package com.jkh.backend.service;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;

import java.util.List;

public interface FlatService {
    void save(Flat flat);
    void delete(Flat flat);

    Flat findFlatByNumber(Integer number);
    List<Flat> findAll();

    // Возвращает флаг, существовала ли уже эта квартира, возможно пригодится в будущем
    boolean addUserToFlat(Flat flat, User user);
}
