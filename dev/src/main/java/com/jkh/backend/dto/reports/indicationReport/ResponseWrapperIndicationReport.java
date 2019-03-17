package com.jkh.backend.dto.reports.indicationReport;

import com.jkh.backend.dto.reports.ResponseWrapperReport;
import com.jkh.backend.model.enums.CounterType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class ResponseWrapperIndicationReport extends ResponseWrapperReport implements Serializable {
    private List<ResponseWrapperIndicationReportRow> rows;
    private Map<CounterType, ResponseWrapperIndicationReportCounter> total;

    public ResponseWrapperIndicationReport(String message) {
        super(message);
        this.rows = new ArrayList<>();
        this.total = new HashMap<>();
    }

    public ResponseWrapperIndicationReport subList(Integer first, Integer last) {
        ResponseWrapperIndicationReport report = SerializationUtils.clone(this);
        report.setRows(rows.subList(first, last));
        return report;
    }
}
