package com.jkh.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter @Setter
public class Flat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @Column(unique = true)
    private Integer number;

//    @ManyToOne
//    @JoinColumn(name = "house_id")
//    private House house;
//
//    @Column(name = "last_registration_date")
//    private Date LastRegistrationDate;
//
//    private Double square;
//
//    @Column(name = "num_of_reg_owners")
//    private Integer NumOfRegOwners;

    @OneToMany(mappedBy = "flat", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Counter> counterSet;

    @OneToMany(mappedBy = "flat", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JsonIgnore
    private Set<User> userSet;
}
