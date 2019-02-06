package com.jkh.backend.model;

import com.jkh.backend.model.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    private String phone;

    @Column(unique = true)
    private String login;

    private String email;

    private String password;

    private Role role;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    private List<AdminHouse> adminHouseList;

//    @ManyToMany
//    @JoinTable(name = "house_admin",
//        joinColumns = @JoinColumn(name = "house_id", referencedColumnName = "id"),
//        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
//    private List<House> houseList;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<AdminHouse> getAdminHouseList() {
        return adminHouseList;
    }

    public void setAdminHouseList(List<AdminHouse> adminHouseList) {
        this.adminHouseList = adminHouseList;
    }
//    public List<House> getHouseList() {
//        return houseList;
//    }
//
//    public void setHouseList(List<House> houseList) {
//        this.houseList = houseList;
//    }
}
