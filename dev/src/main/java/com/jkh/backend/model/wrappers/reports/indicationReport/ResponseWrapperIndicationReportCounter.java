package com.jkh.backend.model.wrappers.reports.indicationReport;

import com.jkh.backend.model.enums.CounterType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ResponseWrapperIndicationReportCounter implements Serializable {
    private CounterType type;
    private String number;
    private Integer value;

    public ResponseWrapperIndicationReportCounter(CounterType type, Integer value) {
        this.type = type;
        this.value = value;
    }
}
