package com.jkh.backend.service;

import com.jkh.backend.model.User;

public interface PeriodCalculatorService {
    Integer countDaysOverDefaultPeriodOfCountersSendingForUser(User user);
    Integer getDefaultPeriodBetweenSendings();
}
