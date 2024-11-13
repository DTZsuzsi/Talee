package com.codecool.controller;// ReportController.java

import com.codecool.DTO.reportDTO.ReportDTO;
import com.codecool.model.reports.ReportType;
import com.codecool.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ReportDTO> getReports(
            @RequestParam(required = false) ReportType reportType,
            @RequestParam(required = false) Boolean issued,
            @RequestParam(required = false) Boolean solved,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return reportService.getAllReports(reportType, issued, solved, sortBy, order);
    }
}
