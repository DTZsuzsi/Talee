package com.codecool.controller;// ReportController.java

import com.codecool.DTO.report.ReportDTO;
import com.codecool.DTO.report.ReportReqDTO;
import com.codecool.DTO.report.ReportResDTO;
import com.codecool.model.reports.ReportType;
import com.codecool.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ReportDTO> getReports(
            @RequestParam(required = false) ReportType reportType,
            @RequestParam(required = false) Boolean issued,
            @RequestParam(required = false) Boolean solved,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return reportService.getAllReports(reportType, issued, solved, sortBy, order);
    }

    @GetMapping("/{id}")
    public ReportDTO getReportById(@PathVariable long id) {
        return reportService.getReportById(id);
    }

    @PostMapping
    public ReportResDTO addReport(@RequestBody ReportReqDTO reportDTO) {
        return reportService.addReport(reportDTO);
    }
}
