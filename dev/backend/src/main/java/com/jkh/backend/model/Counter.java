package com.jkh.backend.model;

import com.jkh.backend.model.enums.CounterType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Counter {
    @Id
    @GeneratedValue
    private Integer id;

    private String number;

    private CounterType type;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    private Integer zero;

    @OneToMany(mappedBy = "counter", fetch = FetchType.LAZY)
    private List<Indication> indicationList;

    public Counter() {
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

    public List<Indication> getIndicationList() {
        return indicationList;
    }

    public void setIndicationList(List<Indication> indicationList) {
        this.indicationList = indicationList;
    }
}
