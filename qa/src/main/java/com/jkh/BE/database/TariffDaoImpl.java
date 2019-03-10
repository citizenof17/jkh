package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

public class TariffDaoImpl extends JdbcDao implements TariffDao {

    private static final String DELETE_ALL_TARIFFS = "DELETE FROM TARIFF";

    public TariffDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_TARIFFS);
    }
}
