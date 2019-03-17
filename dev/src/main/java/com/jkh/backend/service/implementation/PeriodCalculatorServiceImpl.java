package com.jkh.backend.service.implementation;

import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReportRow;
import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.service.CommonService;
import com.jkh.backend.service.IndicationService;
import com.jkh.backend.service.PeriodCalculatorService;
import com.jkh.backend.utils.CommonKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PeriodCalculatorServiceImpl implements PeriodCalculatorService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private IndicationService indicationService;

    public Integer countDaysOverDefaultPeriodOfCountersSendingForUser(User user) {
        Integer defaultPeriodBetweenSendings = Integer.parseInt(commonService.findCommonByKey(
                CommonKeys.DEFAULT_PERIOD_BETWEEN_COUNTERS_SENDINGS).getValue());

        if (user.getRole().equals(Role.USER)) {
            List<ResponseWrapperIndicationReportRow> lastIndication = indicationService.getLastNIndications(user, 1);

            if (lastIndication.isEmpty()) {
                return -1;
            } else {
                LocalDate lastIndicationSubmitDate = lastIndication.get(0).getDate();
                Integer diff = Math.toIntExact(DAYS.between(lastIndicationSubmitDate, LocalDate.now()));

                return Math.max(0, diff - defaultPeriodBetweenSendings);
            }
        }

        return -100500;
    }
}
