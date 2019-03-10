package com.jkh.backend.service;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.wrappers.ResponseWrapperIndicationReportRow;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.List;

public interface IndicationService {
    void save(Indication indication);

    JSONObject addIndication(Flat flat, Indication indication);

    JSONObject addIndications(List<Indication> indications);

    Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter);

    List<ResponseWrapperIndicationReportRow> getLastNIndications(Integer n);

    List<ResponseWrapperIndicationReportRow> getIndications();
    List<ResponseWrapperIndicationReportRow> getIndications(Flat flat);
    List<ResponseWrapperIndicationReportRow> getIndications(LocalDateTime left, LocalDateTime right);
    List<ResponseWrapperIndicationReportRow> getIndications(Flat flat, LocalDateTime left, LocalDateTime right);
}
