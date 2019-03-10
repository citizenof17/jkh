package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

public class FlatDaoImpl extends JdbcDao implements FlatDao{

    private static final String DELETE_ALL_FLATS = "DELETE FROM FLAT";

    public FlatDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_FLATS);
    }
}
