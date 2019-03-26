package com.jkh.BE.models;

import java.util.Set;

public class Counter {

    private Integer id;
    private String number;
    //@JsonProperty(value = "counter_type")
    private CounterType type;
    //@JsonProperty(value = "flat_id")
    private Flat flat;
    private Integer zero = 0;
    private Set<IndicationRequest> indicationSet;

    public Counter() {

    }

    public Counter(CounterType type) {
        this.type = type;
    }


    public Counter(CounterType type, Flat flat) {
        this.type = type;
        this.flat = flat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CounterType getType() {
        return type;
    }

    public void setType(CounterType type) {
        this.type = type;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Integer getZero() {
        return zero;
    }

    public void setZero(Integer zero) {
        this.zero = zero;
    }

    public Set<IndicationRequest> getIndicationSet() {
        return indicationSet;
    }

    public void setIndicationSet(Set<IndicationRequest> indicationSet) {
        this.indicationSet = indicationSet;
    }

    public enum CounterType {
        COLD_WATER,
        HOT_WATER,
        ELECTRICITY,
        GAS,
        INVALID
    }
}
