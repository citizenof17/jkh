package com.jkh.BE.models;

import java.io.Serializable;
import java.util.Objects;

public class Flat implements Serializable {

    private int number;

    public Flat() {
    }

    public Flat(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return Objects.equals(number, flat.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Flat{" + "number=" + number + '}';
    }
}
