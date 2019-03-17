package com.jkh.backend.service;

import com.jkh.backend.model.Common;

public interface CommonService {
    void save(Common common);

    Common findCommonByKey(String key);
}
