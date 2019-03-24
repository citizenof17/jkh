package com.jkh.backend.service;

import com.jkh.backend.dto.ResponseWrapperStateWithMessages;
import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.User;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReportRow;
import com.jkh.backend.model.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface IndicationService {
    void save(Indication indication);

    String addIndication(Flat flat, Indication indication);

    ResponseWrapperStateWithMessages addIndications(List<Indication> indications);

    Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter);

    List<ResponseWrapperIndicationReportRow> getLastNIndications(User user, Integer n);

    List<ResponseWrapperIndicationReportRow> getIndications();
    List<ResponseWrapperIndicationReportRow> getIndications(Flat flat);
    List<ResponseWrapperIndicationReportRow> getIndications(Status status);
    List<ResponseWrapperIndicationReportRow> getIndications(LocalDateTime left, LocalDateTime right);
    List<ResponseWrapperIndicationReportRow> getIndications(Flat flat, LocalDateTime left, LocalDateTime right);
    List<ResponseWrapperIndicationReportRow> getIndications(Status status, LocalDateTime left, LocalDateTime right);
    List<ResponseWrapperIndicationReportRow> getIndications(Status status, Flat flat);
    List<ResponseWrapperIndicationReportRow> getIndications(Status status, Flat flat, LocalDateTime left, LocalDateTime right);
}
