package com.jkh.backend.model;

import com.jkh.backend.model.enums.CounterType;

import javax.persistence.*;

@Entity
public class Counter {
    @Id
    @GeneratedValue
    private Integer id;

    private String number;

    private CounterType type;

//    @ManyToOne
//    @JoinColumn(name = "flat_id")
//    private Flat flat;

    private Integer zero;

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

//    public Flat getFlat() {
//        return flat;
//    }
//
//    public void setFlat(Flat flat) {
//        this.flat = flat;
//    }

    public Integer getZero() {
        return zero;
    }

    public void setZero(Integer zero) {
        this.zero = zero;
    }
}
