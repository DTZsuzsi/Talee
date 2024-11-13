package com.codecool.service;

import com.codecool.DTO.reportDTO.ReportDTO;
import com.codecool.DTO.reportDTO.ReportReqDTO;
import com.codecool.DTO.reportDTO.ReportResDTO;
import com.codecool.model.reports.Report;
import com.codecool.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

public class ReportService {
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ReportDTO getReportById(long reportId) {
        var report = reportRepository.findById(reportId).orElse(null);
        if(report != null) {
            return new ReportDTO(report.getId(),
                                 report.getTitle(),
                                 report.getDescription(),
                                 report.getReportType(),
                                 report.isIssued(),
                                 report.isSolved(),
                                 report.getCreatedAt(),
                                 report.getUpdatedAt());
        }
        return null;
    }
    public Stream<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream().map(report ->
                new ReportDTO(report.getId(),
                              report.getTitle(),
                              report.getDescription(),
                              report.getReportType(),
                              report.isIssued(),
                              report.isSolved(),
                              report.getCreatedAt(),
                              report.getUpdatedAt()));
    }

    public ReportResDTO addReport(ReportReqDTO reportRequest) {
        var report = new Report();
        report.setTitle(reportRequest.title());
        report.setDescription(reportRequest.description());
        report.setReportType(reportRequest.type());
        reportRepository.saveAndFlush(report);
        return new ReportResDTO(report.getId(), report.getTitle(),
                                report.getCreatedAt());
    }
}
