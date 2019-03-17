package com.jkh.backend.model.custom;

import com.jkh.backend.model.Flat;

import java.time.LocalDate;
import java.util.Objects;

public class CustomPair {

    private LocalDate localDate;
    private Flat flat;

    public CustomPair() {
    }

    public CustomPair(LocalDate localDate, Flat flat) {
        this.localDate = localDate;
        this.flat = flat;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomPair that = (CustomPair) o;
        return Objects.equals(localDate, that.localDate) && Objects.equals(flat, that.flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate, flat);
    }

    @Override
    public String toString() {
        return "CustomPair{" + "localDate=" + localDate + ", flat=" + flat + '}';
    }
}


