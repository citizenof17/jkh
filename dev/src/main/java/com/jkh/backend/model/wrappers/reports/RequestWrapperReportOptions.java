package com.jkh.backend.model.wrappers.reports;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jkh.backend.model.Flat;
import com.jkh.backend.model.enums.ReportOptionsStandardPeriod;
import com.jkh.backend.model.enums.ReportOptionsType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter @Setter
public class RequestWrapperReportOptions implements Serializable {
    private LocalDate leftDate;
    private LocalDate rightDate;
    private ReportOptionsStandardPeriod standardPeriod;
    private ReportOptionsType type;
    private Flat flat;

    @JsonIgnore
    private LocalDateTime left;

    @JsonIgnore
    private LocalDateTime right;

    @JsonIgnore
    private String message;

    @JsonIgnore
    private static final LocalTime startOfDay = LocalTime.MIDNIGHT;

    @JsonIgnore
    private static final LocalTime endOfDay = LocalTime.MIDNIGHT.minusNanos(1);

    public void normalizePeriod() {
        LocalDateTime now = LocalDateTime.now();
        switch (standardPeriod) {
            case MANUAL:
                DateTimeFormatter dateTimeFormatter =
                        DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"));
                if (type.equals(ReportOptionsType.WHO_DID_NOT_SEND)) {
                    leftDate = leftDate.withDayOfMonth(1);
                    rightDate = rightDate.withDayOfMonth(1).plusMonths(1).minusDays(1);

                    dateTimeFormatter = DateTimeFormatter.ofPattern("MM.yyyy", new Locale("ru"));
                }
                message = "за период с " + dateTimeFormatter.format(leftDate) +
                        " по " + dateTimeFormatter.format(rightDate);
                break;
            case THIS_MONTH:
                leftDate = LocalDate.of(now.getYear(), now.getMonth(), 1);
                rightDate = leftDate.plusMonths(1).minusDays(1);
                message = "за "
                        + DateTimeFormatter.ofPattern("LLLL yyyy", new Locale("ru")).format(leftDate)
                        + " года";
                break;
            case THIS_YEAR:
                leftDate = LocalDate.of(now.getYear(), Month.JANUARY, 1);
                rightDate = leftDate.plusYears(1).minusDays(1);
                message = "за "
                        + DateTimeFormatter.ofPattern("yyyy", new Locale("ru")).format(leftDate)
                        + " год";
                break;
            case ALL:
                left = null;
                right = null;
                message = "за весь период";
                return;
        }
        left = LocalDateTime.of(leftDate, startOfDay);
        right = LocalDateTime.of(rightDate, endOfDay);
    }
}
