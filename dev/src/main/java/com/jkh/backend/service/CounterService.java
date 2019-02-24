package com.jkh.backend.service;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.enums.CounterType;

public interface CounterService {
    void save(Counter counter);

    void addThreeCounters(Flat flat);

    Counter findCounterByFlatAndType(Flat flat, CounterType counterType);
    Counter findCounterByFlatAndNumber(Flat flat, String number);
    Counter findCounterByFlatAndIndication(Flat flat, Indication indication);
}
