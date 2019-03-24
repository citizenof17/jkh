package com.jkh.BE.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class IndicationRequest {

    private Integer id;
    private LocalDateTime date;
    //@JsonProperty(value = "counter_id")
    private Counter counter;
    private Integer value;

    public IndicationRequest() {

    }

    public IndicationRequest(Counter counter, Integer value) {
        this.counter = counter;
        this.value = value;
    }

    public IndicationRequest(Integer id, LocalDateTime date, Counter counter, Integer value) {
        this.id = id;
        this.date = date;
        this.counter = counter;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Counter getCounter() {
        return counter;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndicationRequest that = (IndicationRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(counter, that.counter) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, counter, value);
    }

    @Override
    public String toString() {
        return "IndicationRequest{" +
                "id=" + id +
                ", date=" + date +
                ", counter=" + counter +
                ", value=" + value +
                '}';
    }
}
