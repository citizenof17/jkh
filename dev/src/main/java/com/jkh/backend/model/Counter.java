package com.jkh.backend.model;

import com.jkh.backend.model.enums.CounterType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class Counter {
    @Id
    @GeneratedValue
    private Integer id;

    private String number;

    @Column(name = "counter_type")
    @Enumerated(EnumType.STRING)
    private CounterType type;

    @ManyToOne
    @JoinColumn(name = "flat_id")
    private Flat flat;

    private Integer zero = 0;

    @OneToMany(mappedBy = "counter", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Indication> indicationSet;

    public Counter() {
    }

    public Counter(CounterType type, Flat flat) {
        this.type = type;
        this.flat = flat;
    }
}
