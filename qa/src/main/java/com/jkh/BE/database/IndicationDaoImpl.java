package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import com.jkh.BE.models.Counter;
import org.springframework.jdbc.core.JdbcTemplate;

public class IndicationDaoImpl extends JdbcDao implements IndicationDao {

    private static final String DELETE_ALL_INDICATIONS = "DELETE FROM indication";
    private static final String SELECT_MAX_VALUE_BY_USER_BY_COUNTER_TYPE =
            "select max(indication.value) as max " +
                    "from indication inner join " +
                    "(select c.id from user inner join counter c " +
                    "on user.login = '%s' and c.counter_type = '%s' and user.flat_id = c.flat_id) as m " +
                    "on indication.counter_id = m.id;";

    public IndicationDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_INDICATIONS);
    }

    @Override
    public Integer select(String login, Counter.CounterType counterType) {
        return (Integer) (getJdbcTemplate().queryForList(String.format(SELECT_MAX_VALUE_BY_USER_BY_COUNTER_TYPE, login, counterType.toString()))).get(0).get("max");
    }
}
