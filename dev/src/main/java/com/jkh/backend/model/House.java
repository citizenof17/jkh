package com.jkh.backend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class House {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String address;

    @OneToMany(mappedBy = "house", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Flat> flatSet;

    @ManyToMany(mappedBy = "houseSet")
    private Set<User> adminSet;

    public House() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Flat> getFlatSet() {
        return flatSet;
    }

    public void setFlatSet(Set<Flat> flatSet) {
        this.flatSet = flatSet;
    }

    public Set<User> getAdminSet() {
        return adminSet;
    }

    public void setAdminSet(Set<User> adminSet) {
        this.adminSet = adminSet;
    }
}
