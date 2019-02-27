package com.jkh.backend.service;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.ResponseWrapperIndicationReport;
import io.swagger.models.auth.In;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.List;

public interface IndicationService {
    void save(Indication indication);

    JSONObject addIndication(Flat flat, Indication indication);

    JSONObject addIndications(List<Indication> indications);

    Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter);

    JSONArray getLastNIndications(Integer n);

    List<ResponseWrapperIndicationReport> getIndications();
    List<ResponseWrapperIndicationReport> getIndications(Flat flat);
    List<ResponseWrapperIndicationReport> getIndications(Date left, Date right);
    List<ResponseWrapperIndicationReport> getIndications(Flat flat, Date left, Date right);
}
