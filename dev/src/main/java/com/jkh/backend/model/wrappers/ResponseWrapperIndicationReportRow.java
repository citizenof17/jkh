package com.jkh.backend.model.wrappers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.serializers.LocalDateSerializer;
import com.jkh.backend.model.enums.CounterType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ResponseWrapperIndicationReportRow implements Serializable {

    private Flat flat;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private Map<CounterType, ResponseWrapperIndicationReportCounter> indicationMap;

    public ResponseWrapperIndicationReportRow(Indication indication) {
        this.flat = indication.getCounter().getFlat();
        this.date = indication.getDate().toLocalDate();
        this.indicationMap = new HashMap<>();
        this.addIndication(indication);
    }

    public void addIndication(Indication indication) {
        CounterType counterType = indication.getCounter().getType();
        indicationMap.put(counterType, new ResponseWrapperIndicationReportCounter(counterType, indication.getValue()));
    }
}
