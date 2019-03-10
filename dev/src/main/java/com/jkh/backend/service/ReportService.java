package com.jkh.backend.service;

import com.jkh.backend.model.wrappers.RequestWrapperReportOptions;
import com.jkh.backend.model.wrappers.ResponseWrapperIndicationReport;

public interface ReportService {
    ResponseWrapperIndicationReport getReport(RequestWrapperReportOptions reportOptions);
}
