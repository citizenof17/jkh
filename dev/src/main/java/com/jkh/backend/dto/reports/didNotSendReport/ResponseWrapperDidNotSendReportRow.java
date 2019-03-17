package com.jkh.backend.dto.reports.didNotSendReport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jkh.backend.model.Flat;
import com.jkh.backend.dto.reports.serializers.LocalDateWithoutDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
class ResponseWrapperDidNotSendReportRow implements Serializable {
    @JsonSerialize(using = LocalDateWithoutDateSerializer.class)
    private LocalDate date;
    private List<Flat> flats;

    ResponseWrapperDidNotSendReportRow(LocalDate date, List<Flat> flats) {
        this.date = date;
        this.flats = flats;
    }
}
