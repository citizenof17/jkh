package com.jkh.backend.repository;

import com.jkh.backend.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Integer> {
    Flat findFlatByNumber(Integer number);
    List<Flat> findAll();
}
