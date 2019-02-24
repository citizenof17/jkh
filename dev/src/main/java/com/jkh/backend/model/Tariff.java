package com.jkh.backend.model;

import com.jkh.backend.model.enums.CounterType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter @Setter
public class Tariff {
    @Id
    @GeneratedValue
    private Integer id;

    private CounterType type;

    private Double cost;

    private Date date;

}
