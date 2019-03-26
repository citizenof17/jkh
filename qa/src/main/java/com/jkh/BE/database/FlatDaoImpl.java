package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class FlatDaoImpl extends JdbcDao implements FlatDao {

    private static final String DELETE_ALL_FLATS = "DELETE FROM flat";
    private static final String SELECT_ALL_FLATS_NUMBERS = "select flat.number from flat";

    public FlatDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_FLATS);
    }

    @Override
    public List<Map<String, Object>> selectAllFlatsNumbers() {
        return getJdbcTemplate().queryForList(SELECT_ALL_FLATS_NUMBERS);
    }
}
