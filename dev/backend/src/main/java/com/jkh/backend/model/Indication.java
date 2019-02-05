package com.jkh.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Indication {
    @Id
    @GeneratedValue
    private Integer id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    private Counter counter;

    private Integer value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
