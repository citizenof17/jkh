package com.jkh.backend.dto.reports.indicationReport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jkh.backend.model.enums.CounterType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapperIndicationReportCounter implements Serializable {
    private CounterType type;
    private String number;
    private Integer value;

    public ResponseWrapperIndicationReportCounter(CounterType type, Integer value) {
        this.type = type;
        this.value = value;
    }
}
