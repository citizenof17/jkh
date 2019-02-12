package com.jkh.backend.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Flat {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @Column(name = "last_registration_date")
    private Date LastRegistrationDate;

    private Double square;

    @Column(name = "num_of_reg_owners")
    private Integer NumOfRegOwners;

    @OneToMany(mappedBy = "flat", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Counter> counterSet;

    @OneToMany(mappedBy = "flat", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Set<User> userSet;

    public Flat() {
    }

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
        return LastRegistrationDate;
    }

    public void setOwnerDate(Date ownerDate) {
        this.LastRegistrationDate = ownerDate;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Integer getAmountRegistered() {
        return NumOfRegOwners;
    }

    public void setAmountRegistered(Integer amountRegistered) {
        this.NumOfRegOwners = amountRegistered;
    }

    public Set<Counter> getCounterSet() {
        return counterSet;
    }

    public void setCounterSet(Set<Counter> counterSet) {
        this.counterSet = counterSet;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
