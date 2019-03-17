package com.jkh.backend.service.implementation;

import com.jkh.backend.model.Common;
import com.jkh.backend.repository.CommonRepository;
import com.jkh.backend.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonRepository commonRepository;

    @Override
    public void save(Common common) {
        commonRepository.save(common);
    }

    @Override
    public Common findCommonByKey(String key) {
        return commonRepository.findCommonByKey(key);
    }

}
