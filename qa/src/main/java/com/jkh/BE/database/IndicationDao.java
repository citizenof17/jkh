package com.jkh.BE.database;

import com.jkh.BE.models.Counter;

public interface IndicationDao {

    void deleteAll();

    Integer select(String login, Counter.CounterType counterType);
}
