package com.jkh.backend.model.wrappers;

import com.jkh.backend.model.Flat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class ResponseWrapperDidNotSendReport implements Serializable {
    private Map<LocalDate, List<Flat>> monthToFlats;
    private Map<Flat, List<LocalDate>> flatToMonths;

    public ResponseWrapperDidNotSendReport() {
        monthToFlats = new HashMap<>();
        flatToMonths = new HashMap<>();
    }

    public void add(LocalDate month, Flat flat) {
        monthToFlats.putIfAbsent(month, new ArrayList<>());
        monthToFlats.get(month).add(flat);

        flatToMonths.putIfAbsent(flat, new ArrayList<>());
        flatToMonths.get(flat).add(month);
    }
}
