package com.jkh.backend.service.implementation;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import com.jkh.backend.model.custom.CustomPair;
import com.jkh.backend.model.enums.CounterType;
import com.jkh.backend.dto.reports.enums.ReportOptionsType;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.dto.reports.RequestWrapperReportOptions;
import com.jkh.backend.dto.reports.ResponseWrapperReport;
import com.jkh.backend.dto.reports.didNotSendReport.ResponseWrapperDidNotSendReport;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReport;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReportCounter;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReportRow;
import com.jkh.backend.service.FlatService;
import com.jkh.backend.service.IndicationService;
import com.jkh.backend.service.ReportService;
import com.jkh.backend.service.UserService;
import com.jkh.backend.service.validation.ReportValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public ResponseWrapperReport getReport(RequestWrapperReportOptions reportOptions) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);

        ResponseWrapperReport report = reportValidator.validateReportOptions(reportOptions, user);
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

            if (reportOptions.getType().equals(ReportOptionsType.STANDARD)) {
                report = new ResponseWrapperIndicationReport(report.getMessage());
                report.setMessage("Отчет " + reportOptions.getMessage());
                ((ResponseWrapperIndicationReport) report).setRows(indicationService.getIndications(
                        reportOptions.getFlat(),
                        reportOptions.getLeft(),
                        reportOptions.getRight()));
                if (((ResponseWrapperIndicationReport) report).getRows().size() == 0) {
                    report.setMessage(EMPTY_LIST);
                }
                ((ResponseWrapperIndicationReport) report).setTotal(calculateTotal(((ResponseWrapperIndicationReport) report)));
            } else if (reportOptions.getType().equals(ReportOptionsType.WHO_DID_NOT_SEND)) {
                report = new ResponseWrapperDidNotSendReport(report.getMessage());
                report.setMessage("Отчет по не предоставившим данные " + reportOptions.getMessage());
                ((ResponseWrapperDidNotSendReport) report).addRows(findWhoDidNotSend(reportOptions));
            }
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

    private Map<LocalDate, List<Flat>> findWhoDidNotSend(RequestWrapperReportOptions reportOptions) {
        List<ResponseWrapperIndicationReportRow> indicationList = indicationService.getIndications(
                reportOptions.getFlat(), reportOptions.getLeft(), reportOptions.getRight());
        List<LocalDate> months = getMonths(reportOptions);
        List<Flat> flats;
        if (reportOptions.getFlat() == null) {
            flats = flatService.findAll();
        } else {
            flats = Collections.singletonList(flatService.findFlatByNumber(reportOptions.getFlat().getNumber()));
        }
        Set<CustomPair> monthFlat = new HashSet<>();
        for (ResponseWrapperIndicationReportRow row : indicationList) {
            monthFlat.add(new CustomPair(row.getDate().withDayOfMonth(1), row.getFlat()));
        }

        Map<LocalDate, List<Flat>> didNotSendReport = new TreeMap<>();
        for (LocalDate month : months) {
            for (Flat flat : flats) {
                if (!monthFlat.contains(new CustomPair(month, flat))) {
                    didNotSendReport.putIfAbsent(month, new ArrayList<>());
                    didNotSendReport.get(month).add(flat);
                }
            }
        }

        return didNotSendReport;
    }

    private List<LocalDate> getMonths(RequestWrapperReportOptions reportOptions) {
        List<LocalDate> months = new ArrayList<>();
        for (LocalDate month = reportOptions.getLeftDate().withDayOfMonth(1);
             month.isBefore(reportOptions.getRightDate().withDayOfMonth(1).plusMonths(1));
             month = month.plusMonths(1)) {
            months.add(month);
        }
        return months;
    }
}
