package com.jkh.backend.repository;

import com.jkh.backend.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}
