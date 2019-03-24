package com.jkh.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Indication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    private Counter counter;

    private Integer value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
