package com.jkh.backend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Flat {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @Column(name = "owner_date")
    private Date ownerDate;

    private Double square;

    @Column(name = "amount_registered")
    private Integer amountRegistered;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Date getOwnerDate() {
        return ownerDate;
    }

    public void setOwnerDate(Date ownerDate) {
        this.ownerDate = ownerDate;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Integer getAmountRegistered() {
        return amountRegistered;
    }

    public void setAmountRegistered(Integer amountRegistered) {
        this.amountRegistered = amountRegistered;
    }
}
