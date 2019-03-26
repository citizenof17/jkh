package com.jkh.BE.database;

import java.util.List;
import java.util.Map;

public interface FlatDao {

    void deleteAll();

    List<Map<String, Object>> selectAllFlatsNumbers();
}
