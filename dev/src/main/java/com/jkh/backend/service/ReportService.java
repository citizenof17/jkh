package com.jkh.backend.service;

import com.jkh.backend.model.wrappers.reports.RequestWrapperReportOptions;
import com.jkh.backend.model.wrappers.reports.ResponseWrapperReport;

public interface ReportService {
    ResponseWrapperReport getReport(RequestWrapperReportOptions reportOptions);
}
