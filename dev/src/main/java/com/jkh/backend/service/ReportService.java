package com.jkh.backend.service;

import com.jkh.backend.dto.reports.RequestWrapperReportOptions;
import com.jkh.backend.dto.reports.ResponseWrapperReport;

public interface ReportService {
    ResponseWrapperReport getReport(RequestWrapperReportOptions reportOptions);
}
