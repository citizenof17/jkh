package com.jkh.backend.service;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;

public interface FlatService {
    void save(Flat flat);

    Flat findFlatByNumber(Integer number);

    // Возвращает флаг, существовала ли уже эта квартира, возможно пригодится в будущем
    boolean addUserToFlat(Flat flat, User user);
}
