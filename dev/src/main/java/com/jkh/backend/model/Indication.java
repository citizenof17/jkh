package com.jkh.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Indication {
    @Id
    @GeneratedValue
    private Integer id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    private Counter counter;

    private Integer value;

}
