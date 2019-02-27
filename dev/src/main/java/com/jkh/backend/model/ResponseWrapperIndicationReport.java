package com.jkh.backend.model;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.enums.CounterType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class ResponseWrapperIndicationReport {
    private class ResponseWrapperCounter {
        private CounterType type;
        private String number;
        private Integer value;

        private ResponseWrapperCounter(CounterType type, Integer value) {
            this.type = type;
            this.value = value;
        }
    }

    private Integer flatNumber;
    private Date date;
    private List<ResponseWrapperCounter> indicationList;

    public ResponseWrapperIndicationReport(Indication indication) {
        Counter counter = indication.getCounter();
        this.flatNumber = counter.getFlat().getNumber();
        this.date = indication.getDate();
        this.indicationList = new ArrayList<>();
        indicationList.add(new ResponseWrapperCounter(counter.getType(), indication.getValue()));
    }

    public void addIndication(Indication indication) {
        indicationList.add(new ResponseWrapperCounter(indication.getCounter().getType(), indication.getValue()));
    }
}
