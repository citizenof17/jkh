package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

public class IndicationDaoImpl extends JdbcDao implements IndicationDao {

    private static final String DELETE_ALL_INDICATIONS = "DELETE FROM INDICATION";

    public IndicationDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_INDICATIONS);
    }
}
