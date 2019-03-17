package com.jkh.backend.repository;

import com.jkh.backend.model.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonRepository extends JpaRepository<Common, Integer> {
    Common findCommonByKey(String key);
}
