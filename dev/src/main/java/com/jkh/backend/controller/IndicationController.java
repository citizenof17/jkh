package com.jkh.backend.controller;

import com.jkh.backend.model.Indication;
import com.jkh.backend.model.wrappers.RequestWrapperReportOptions;
import com.jkh.backend.model.wrappers.ResponseWrapperIndicationReport;
import com.jkh.backend.model.wrappers.ResponseWrapperIndicationReportRow;
import com.jkh.backend.service.IndicationService;
import com.jkh.backend.service.ReportService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndicationController {

    @Autowired
    private IndicationService indicationService;

    @Autowired
    private ReportService reportService;

    @ResponseBody
    @RequestMapping(value = "/sendIndications",  method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<JSONObject> sendIndication(@RequestBody List<Indication> indications) {

        JSONObject json = indicationService.addIndications(indications);
        if (json.get("isOk").equals(true)) {
            return new ResponseEntity<>(json, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(json, HttpStatus.CONFLICT);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/lastNIndications")
    public ResponseEntity<List<ResponseWrapperIndicationReportRow>> lastNIndications() {
        List<ResponseWrapperIndicationReportRow> json = indicationService.getLastNIndications(50);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/report", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<ResponseWrapperIndicationReport> getReport(
            @RequestBody RequestWrapperReportOptions reportOptions) {
        ResponseWrapperIndicationReport report = reportService.getReport(reportOptions);
        if (report.isOk()) {
            return new ResponseEntity<>(report, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(report, HttpStatus.CONFLICT);
        }
    }
}

