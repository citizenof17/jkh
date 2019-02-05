package com.jkh.backend.repository;

import com.jkh.backend.model.Indication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicationRepository extends JpaRepository<Indication, Integer> {
}
