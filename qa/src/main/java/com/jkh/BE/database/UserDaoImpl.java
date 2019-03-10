package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl extends JdbcDao implements UserDao {

    private static final String DELETE_ALL_USERS = "DELETE FROM USER";

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll()  {
        getJdbcTemplate().update(DELETE_ALL_USERS);
    }
}
