package com.jkh.BE.database;

import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends JdbcDao implements UserDao {

    private static final String DELETE_ALL_USERS = "DELETE FROM user WHERE user.role != 'ADMIN'";
    private static final String SELECT_USER_BY_LOGIN = "select user.login, user.name, flat.number, user.email, " +
            "user.phone, user.status from user inner join flat on user.flat_id = flat.id and user.login = '%s';";
    private static final String SELECT_USER_BY_FLAT = "select user.name, user.login, user.phone, user.email, flat.number " +
            "from user inner join flat on user.flat_id = flat.id and flat.number = '%s';";
    private static final String UPDATE_ALL_USERS_TO_ACTIVE = "update user set user.status = 'ACTIVE'";

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate, MetricRegistry metricRegistry) {
        super(jdbcTemplate, metricRegistry);
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update(DELETE_ALL_USERS);
    }

    @Override
    public Map<String, Object> selectUserByLogin(String login) {
        return getJdbcTemplate().queryForList(String.format(SELECT_USER_BY_LOGIN, login)).get(0);
    }

    @Override
    public List<Map<String, Object>> selectUserByFlat(Integer flat) {
        return getJdbcTemplate().queryForList(String.format(SELECT_USER_BY_FLAT, flat.toString()));
    }

    @Override
    public void updateAllUsersToActive() {
        getJdbcTemplate().update(UPDATE_ALL_USERS_TO_ACTIVE);
    }
}
