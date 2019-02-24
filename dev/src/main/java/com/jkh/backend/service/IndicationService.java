package com.jkh.backend.service;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import org.json.simple.JSONObject;

import java.util.List;

public interface IndicationService {
    void save(Indication indication);

    JSONObject addIndication(Flat flat, Indication indication);

    JSONObject addIndications(List<Indication> indications);

    Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter);
}
