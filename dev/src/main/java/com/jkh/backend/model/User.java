package com.jkh.backend.model;

import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(name = "admin_house",
//        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(name = "house_id", referencedColumnName = "id")})
//    private Set<House> houseSet;

}
