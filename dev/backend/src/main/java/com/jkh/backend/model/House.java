package com.jkh.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class House {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String address;

    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    private List<Flat> flatList;

    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    private List<AdminHouse> adminHouseList;

//    @ManyToMany(mappedBy = "houses")
//    private List<User> adminList;

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

    public List<Flat> getFlatList() {
        return flatList;
    }

    public void setFlatList(List<Flat> flatList) {
        this.flatList = flatList;
    }

    public List<AdminHouse> getAdminHouseList() {
        return adminHouseList;
    }

    public void setAdminHouseList(List<AdminHouse> adminHouseList) {
        this.adminHouseList = adminHouseList;
    }
//    public List<User> getAdminList() {
//        return adminList;
//    }
//
//    public void setAdminList(List<User> adminList) {
//        this.adminList = adminList;
//    }
}
