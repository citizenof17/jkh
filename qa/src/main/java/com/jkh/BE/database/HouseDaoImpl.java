package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

public class HouseDaoImpl extends JdbcDao implements HouseDao {

    private static final String DELETE_ALL_HOUSES = "DELETE FROM HOUSE";

    public HouseDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_HOUSES);
    }
}
