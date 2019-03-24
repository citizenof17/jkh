package com.jkh.backend.service;

import com.jkh.backend.dto.reports.RequestWrapperReportOptions;
import com.jkh.backend.dto.reports.indicationReport.ResponseWrapperIndicationReport;

public interface ReportService {
    ResponseWrapperIndicationReport getReport(RequestWrapperReportOptions reportOptions);
}
