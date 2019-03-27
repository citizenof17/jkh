package com.jkh.BE.database;

import com.jkh.BE.models.Counter;

import java.util.List;
import java.util.Map;

public interface IndicationDao {

    void deleteAll();

    Integer select(String login, Counter.CounterType counterType);

    List<Map<String, Object>> selectIndicationByDate(String date);

    List<Map<String, Object>> selectDates(String login);
}
