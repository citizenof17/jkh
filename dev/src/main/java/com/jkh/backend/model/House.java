package com.jkh.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class House {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String address;

//    @OneToMany(mappedBy = "house", orphanRemoval = true, cascade = CascadeType.ALL)
//    private Set<Flat> flatSet;
//
//    @ManyToMany(mappedBy = "houseSet")
//    private Set<User> adminSet;

}
