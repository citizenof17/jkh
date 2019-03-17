package com.jkh.backend.controller;

import com.jkh.backend.dto.reports.RequestWrapperReportOptions;
import com.jkh.backend.dto.reports.ResponseWrapperReport;
import com.jkh.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReportController {

    private static final String REPORT_ENDPOINT = "/report";

    @Autowired
    private ReportService reportService;

    @ResponseBody
    @RequestMapping(value = REPORT_ENDPOINT, method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<ResponseWrapperReport> getReport(@RequestBody RequestWrapperReportOptions reportOptions) {

        ResponseWrapperReport report = reportService.getReport(reportOptions);
        if (report.isOk()) {
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(report, HttpStatus.CONFLICT);
        }
    }
}
