package com.jkh.backend.model.wrappers;

import com.jkh.backend.model.enums.CounterType;
import com.jkh.backend.service.validation.ValidationMessages;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class ResponseWrapperIndicationReport implements Serializable {
    private boolean isOk;
    private String message;
    private List<ResponseWrapperIndicationReportRow> rows;
    private Map<CounterType, ResponseWrapperIndicationReportCounter> total;
    private ResponseWrapperDidNotSendReport didNotSend;

    public ResponseWrapperIndicationReport(String message) {
        this.message = message;
        this.isOk = this.message.equals(ValidationMessages.OK);
        this.rows = new ArrayList<>();
        this.total = new HashMap<>();
    }

    public ResponseWrapperIndicationReport subList(Integer first, Integer last) {
        ResponseWrapperIndicationReport report = SerializationUtils.clone(this);
        report.setRows(rows.subList(first, last));
        return report;
    }
}
