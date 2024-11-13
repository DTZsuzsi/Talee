package com.codecool.service;// ReportService.java

import com.codecool.DTO.reportDTO.ReportDTO;
import com.codecool.DTO.reportDTO.ReportReqDTO;
import com.codecool.DTO.reportDTO.ReportResDTO;
import com.codecool.model.reports.Report;
import com.codecool.model.reports.ReportType;
import com.codecool.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ReportDTO> getAllReports(ReportType reportType, Boolean issued, Boolean solved, String sortBy, String order) {
        // Determine the sort direction based on the `order` parameter
        final Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final String sortField = sortBy != null ? sortBy : "id"; // Default to "id" if sortBy is null

        // Fetch filtered and sorted reports from the repository
        return reportRepository.findAll().stream()
                               .filter(report -> (reportType == null || report.getReportType() == reportType))
                               .filter(report -> (issued == null || report.isIssued() == issued))
                               .filter(report -> (solved == null || report.isSolved() == solved))
                               .sorted((report1, report2) -> {
                                   // Custom comparator logic using `direction`
                                   if ("createdAt".equals(sortField)) {
                                       return direction == Sort.Direction.DESC
                                               ? report2.getCreatedAt().compareTo(report1.getCreatedAt())
                                               : report1.getCreatedAt().compareTo(report2.getCreatedAt());
                                   } else if ("title".equals(sortField)) {
                                       return direction == Sort.Direction.DESC
                                               ? report2.getTitle().compareTo(report1.getTitle())
                                               : report1.getTitle().compareTo(report2.getTitle());
                                   }
                                   // Default case if no sorting field matches
                                   return 0;
                               })
                               .map(report -> new ReportDTO(
                                       report.getId(),
                                       report.getTitle(),
                                       report.getDescription(),
                                       report.getReportType(),
                                       report.isIssued(),
                                       report.isSolved(),
                                       report.getCreatedAt(),
                                       report.getUpdatedAt()))
                               .collect(Collectors.toList());
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
}
