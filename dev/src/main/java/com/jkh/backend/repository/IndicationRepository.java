package com.jkh.backend.repository;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicationRepository extends JpaRepository<Indication, Integer> {

    Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter);
}
