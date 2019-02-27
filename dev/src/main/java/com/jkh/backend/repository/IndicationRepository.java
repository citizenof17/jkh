package com.jkh.backend.repository;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IndicationRepository extends JpaRepository<Indication, Integer> {

    Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter);

    List<Indication> findIndicationsByOrderByDate();
    List<Indication> findIndicationsByCounterOrderByDate(Counter counter);
    List<Indication> findIndicationsByDateBetweenOrderByDate(Date left, Date right);
    List<Indication> findIndicationsByCounterAndDateBetweenOrderByDate(Counter counter, Date left, Date right);
}
