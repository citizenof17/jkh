package com.jkh.backend.model;

import com.jkh.backend.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
public class User implements Serializable {
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    private boolean isActive;

//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(name = "admin_house",
//        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(name = "house_id", referencedColumnName = "id")})
//    private Set<House> houseSet;

}
