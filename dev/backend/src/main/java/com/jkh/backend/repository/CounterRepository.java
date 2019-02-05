package com.jkh.backend.repository;

import com.jkh.backend.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Integer> {
}
