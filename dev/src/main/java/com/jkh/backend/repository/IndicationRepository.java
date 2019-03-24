package com.jkh.backend.repository;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Indication;
import com.jkh.backend.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select ind from Indication ind " +
            "where ind.user.status = :status " +
            "order by ind.date")
    List<Indication> findIndicationsByUserStatusOrderByDate(@Param("status") Status status);

    @Query("select ind from Indication ind " +
            "where ind.user.status = :status " +
            "and ind.counter = :counter " +
            "order by ind.date")
    List<Indication> findIndicationsByUserStatusAndCounterOrderByDate(
            @Param("status") Status status, @Param("counter") Counter counter);

    @Query("select ind from Indication ind " +
            "where ind.user.status = :status " +
                "and ind.date between :left and :right " +
            "order by ind.date")
    List<Indication> findIndicationsByUserStatusAndDateBetweenOrderByDate(
            @Param("status") Status status, @Param("left") LocalDateTime left, @Param("right") LocalDateTime right);

    @Query("select ind from Indication ind " +
            "where ind.user.status = :status " +
            "and ind.date between :left and :right " +
            "and ind.counter = :counter " +
            "order by ind.date")
    List<Indication> findIndicationsByUserStatusAndCounterAndDateBetweenOrderByDate(
            @Param("status") Status status, @Param("counter") Counter counter,
            @Param("left") LocalDateTime left, @Param("right") LocalDateTime right);
}
