package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

public class CounterDaoImpl extends JdbcDao implements CounterDao {

    private static final String DELETE_ALL_COUNTERS = "DELETE FROM counter";

    public CounterDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_COUNTERS);
    }
}
