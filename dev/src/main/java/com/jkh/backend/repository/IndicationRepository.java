package com.jkh.backend.repository;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IndicationRepository extends JpaRepository<Indication, Integer> {

    Indication findDistinctTopIndicationByCounterOrderByDateDesc(Counter counter);

    List<Indication> findIndicationsByOrderByDate();
    List<Indication> findIndicationsByCounterOrderByDate(Counter counter);
    List<Indication> findIndicationsByDateBetweenOrderByDate(LocalDateTime left, LocalDateTime right);
    List<Indication> findIndicationsByCounterAndDateBetweenOrderByDate(Counter counter, LocalDateTime left, LocalDateTime right);
}
