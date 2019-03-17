package com.jkh.backend.dto.reports.didNotSendReport;

import com.jkh.backend.dto.reports.ResponseWrapperReport;
import com.jkh.backend.model.Flat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class ResponseWrapperDidNotSendReport extends ResponseWrapperReport implements Serializable {
    private List<ResponseWrapperDidNotSendReportRow> didNotSend;

    public ResponseWrapperDidNotSendReport(String message) {
        super(message);
        this.didNotSend = new ArrayList<>();
    }

    private void addRow(LocalDate date, List<Flat> flats) {
        this.didNotSend.add(new ResponseWrapperDidNotSendReportRow(date, flats));
    }

    public void addRows(Map<LocalDate, List<Flat>> map) {
        for (LocalDate date : map.keySet()) {
            addRow(date, map.get(date));
        }
    }
}
