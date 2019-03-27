package com.jkh.BE.database;

import java.util.List;
import java.util.Map;

public interface UserDao {

    void deleteAll();

    Map<String, Object> selectUserByLogin(String login);

    List<Map<String, Object>> selectUserByFlat(Integer flat);

    void updateAllUsersToActive();
}
