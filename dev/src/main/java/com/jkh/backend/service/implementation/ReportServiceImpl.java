package com.jkh.backend.service.implementation;

import com.jkh.backend.dto.reports.RequestWrapperReportOptions;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReport;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReportCounter;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReportRow;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.CounterType;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.service.FlatService;
import com.jkh.backend.service.IndicationService;
import com.jkh.backend.service.ReportService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.service.validation.ReportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.jkh.backend.service.validation.ValidationMessages.EMPTY_LIST;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportValidator reportValidator;

    @Autowired
    IndicationService indicationService;

    @Autowired
    UserService userService;

    @Autowired
    FlatService flatService;

    @Override
    public ResponseWrapperIndicationReport getReport(RequestWrapperReportOptions reportOptions) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);

        ResponseWrapperIndicationReport report = reportValidator.validateReportOptions(reportOptions, user);
        if (report.isOk()) {
            reportOptions.normalizePeriod();
            if (user.getRole().equals(Role.USER)) {
                reportOptions.setFlat(user.getFlat());
            } else if (reportOptions.getFlat() != null) {
                reportOptions.setFlat(flatService.findFlatByNumber(reportOptions.getFlat().getNumber()));
            }

            if (reportOptions.getFlat() != null) {
                reportOptions.setMessage(reportOptions.getMessage() +
                        " для квартиры " + reportOptions.getFlat().getNumber());
            }
            if (reportOptions.getStatus() != null) {
                reportOptions.setMessage(reportOptions.getMessage() +
                        " по пользователям со статусом " + reportOptions.getStatus().getRussianName());
            }

            report.setMessage("Отчет " + reportOptions.getMessage());
            report.setRows(indicationService.getIndications(
                    reportOptions.getStatus(),
                    reportOptions.getFlat(),
                    reportOptions.getLeft(),
                    reportOptions.getRight()));
            if (report.getRows().size() == 0) {
                report.setMessage(EMPTY_LIST);
            }
            report.setTotal(calculateTotal(report));
        }

        return report;
    }

    private Map<CounterType, ResponseWrapperIndicationReportCounter> calculateTotal(
            ResponseWrapperIndicationReport report) {
        Map<Flat, Map<CounterType, ResponseWrapperIndicationReportCounter>> firsts = new HashMap<>();
        Map<Flat, Map<CounterType, ResponseWrapperIndicationReportCounter>> lasts = new HashMap<>();
        for (ResponseWrapperIndicationReportRow row : report.getRows()) {
            Flat flat = row.getFlat();
            Map<CounterType, ResponseWrapperIndicationReportCounter> indicationMap = row.getIndicationMap();
            lasts.putIfAbsent(flat, indicationMap);
            firsts.put(flat, indicationMap);
        }

        Map<Flat, Map<CounterType, ResponseWrapperIndicationReportCounter>> flatTotals = new HashMap<>();
        for (Flat flat : firsts.keySet()) {
            flatTotals.put(flat, new HashMap<>());
            for (CounterType counterType : firsts.get(flat).keySet()) {
                flatTotals.get(flat).put(counterType,
                        new ResponseWrapperIndicationReportCounter(counterType,
                                lasts.get(flat).get(counterType).getValue()
                                        - firsts.get(flat).get(counterType).getValue()));
            }
        }

        Map<CounterType, ResponseWrapperIndicationReportCounter> total = new HashMap<>();
        for (Map<CounterType, ResponseWrapperIndicationReportCounter> map : flatTotals.values()) {
            for (CounterType counterType : map.keySet()) {
                total.merge(counterType, map.get(counterType),
                        (oldVal, newVal) -> new ResponseWrapperIndicationReportCounter(
                                counterType, oldVal.getValue() + newVal.getValue()));
            }
        }

        return total;
    }
}
