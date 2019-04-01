package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import com.jkh.BE.models.Counter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class IndicationDaoImpl extends JdbcDao implements IndicationDao {

    private static final String DELETE_ALL_INDICATIONS = "DELETE FROM indication";
    private static final String SELECT_MAX_VALUE_BY_USER_BY_COUNTER_TYPE =
            "select max(indication.value) as max " +
                    "from indication inner join " +
                    "(select c.id from user inner join counter c " +
                    "on user.login = '%s' and c.counter_type = '%s' and user.flat_id = c.flat_id) as m " +
                    "on indication.counter_id = m.id;";

    private static final String SELECT_INDICATIONS_BY_DATE = "select p1.electr, p3.hot, p3.cold from " +
            "(select indication.value as electr from indication inner join counter on indication.counter_id = counter.id" +
            " and indication.date = \"%s\" and counter.counter_type = \"ELECTRICITY\") as p1 inner join " +
            "(select p1.cold, p2.hot from (select indication.value as cold from indication inner join counter on" +
            " indication.counter_id = counter.id and indication.date = \"%s\" and counter.counter_type = \"COLD_WATER\") as p1 " +
            "inner join (select indication.value as hot from indication inner join counter on indication.counter_id = counter.id " +
            "and indication.date = \"%s\" and counter.counter_type = \"HOT_WATER\") as p2) as p3;";

    private static final String SELECT_DATES = "select p1.date as ind_time from (select indication.date, indication.counter_id as id from " +
            "indication inner join user on indication.user_id = user.id and user.login = '%s') as p1 inner join counter " +
            "on counter.id = p1.id and counter.counter_type = 'HOT_WATER'";

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

    @Override
    public List<Map<String, Object>> selectDates(String login) {
        return getJdbcTemplate().queryForList(String.format(SELECT_DATES, login));
    }

    @Override
    public Map<String, Object> selectIndicationByDate(String date) {
        return getJdbcTemplate().queryForList(String.format(SELECT_INDICATIONS_BY_DATE, date, date, date)).get(0);
    }
}
