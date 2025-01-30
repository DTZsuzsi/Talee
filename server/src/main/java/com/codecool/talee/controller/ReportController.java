package com.codecool.talee.controller;// ReportController.java

import com.codecool.talee.DTO.report.ReportDTO;
import com.codecool.talee.DTO.report.ReportReqDTO;
import com.codecool.talee.DTO.report.ReportResDTO;
import com.codecool.talee.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ReportDTO> getReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ReportDTO getReportById(@PathVariable long id) {
        return reportService.getReportById(id);
    }

    @PostMapping
    public ResponseEntity<ReportDTO> addReport(@RequestBody ReportReqDTO reportDTO) {
        return new ResponseEntity<>(reportService.createReport(reportDTO), HttpStatus.CREATED);
    }
}
