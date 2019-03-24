package com.jkh.backend.service.validation;

import com.jkh.backend.dto.reports.RequestWrapperReportOptions;
import com.jkh.backend.dto.reports.enums.ReportOptionsStandardPeriod;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReport;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.jkh.backend.service.validation.ValidationMessages.*;

@Service
public class ReportValidator {

    @Autowired
    private FlatService flatService;

    public ResponseWrapperIndicationReport validateReportOptions(RequestWrapperReportOptions reportOptions, User user) {
        if (reportOptions.getStandardPeriod() == null) {
            reportOptions.setStandardPeriod(ReportOptionsStandardPeriod.MANUAL);
        }
        if (reportOptions.getFlat() != null && reportOptions.getFlat().getNumber() == null) {
            reportOptions.setFlat(null);
        }

        String message = "";
        Flat flat = reportOptions.getFlat();

        if (reportOptions.getStandardPeriod().equals(ReportOptionsStandardPeriod.MANUAL)) {
            LocalDate leftDate = reportOptions.getLeftDate();
            LocalDate rightDate = reportOptions.getRightDate();
            if (leftDate == null || rightDate == null || leftDate.isAfter(rightDate)) {
                message += REPORT_OPTIONS_DATE + "\n";
            }
        }
        if (flat != null && flatService.findFlatByNumber(flat.getNumber()) == null) {
            message += FLAT_NUMBER_INCORRECT + "\n";
        }
        if ((flat != null || reportOptions.getStatus() != null) && user.getRole().equals(Role.USER)) {
            message += FORBIDDEN + "\n";
        }

        if (message.length() == 0) {
            message = OK;
        }
        return new ResponseWrapperIndicationReport(message);
    }
}
