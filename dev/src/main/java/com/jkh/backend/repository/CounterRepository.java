package com.jkh.backend.repository;

import com.jkh.backend.model.Counter;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.enums.CounterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Integer> {

    Counter findCounterByFlatAndType(Flat flat, CounterType counterType);

    Counter findCounterByFlatAndNumber(Flat flat, String number);
}
