package com.codecool.talee.service;
import com.codecool.talee.DTO.report.ReportDTO;
import com.codecool.talee.DTO.report.ReportReqDTO;
import com.codecool.talee.DTO.report.ReportResDTO;
import com.codecool.talee.mapper.ReportMapper;
import com.codecool.talee.model.reports.Report;
import com.codecool.talee.model.reports.ReportType;
import com.codecool.talee.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper=ReportMapper.INSTANCE;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ReportDTO> getAllReports(ReportType reportType, Boolean issued, Boolean solved, String sortBy, String order) {
        final Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final String sortField = (sortBy != null) ? sortBy : "id";

        List<Report> filteredReports = reportRepository.findAll().stream()
                .filter(report -> isMatchingReportType(report, reportType))
                .filter(report -> isMatchingIssuedStatus(report, issued))
                .filter(report -> isMatchingSolvedStatus(report, solved))
                .collect(Collectors.toList());

        List<Report> sortedReports = filteredReports.stream()
                .sorted((report1, report2) -> compareReports(report1, report2, sortField, direction))
                .collect(Collectors.toList());

        return sortedReports.stream()
                .map(reportMapper::reportToReportDTO)
                .collect(Collectors.toList());
    }

    private boolean isMatchingReportType(Report report, ReportType reportType) {
        return reportType == null || report.getReportType() == reportType;
    }

    private boolean isMatchingIssuedStatus(Report report, Boolean issued) {
        return issued == null || report.isIssued() == issued;
    }

    private boolean isMatchingSolvedStatus(Report report, Boolean solved) {
        return solved == null || report.isSolved() == solved;
    }

    private int compareReports(Report report1, Report report2, String sortField, Sort.Direction direction) {
        switch (sortField) {
            case "createdAt":
                return compareValues(report1.getCreatedAt(), report2.getCreatedAt(), direction);
            case "title":
                return compareValues(report1.getTitle(), report2.getTitle(), direction);
            default:
                return 0;
        }
    }

    private <T extends Comparable<T>> int compareValues(T value1, T value2, Sort.Direction direction) {
        return (direction == Sort.Direction.DESC)
                ? value2.compareTo(value1)
                : value1.compareTo(value2);
    }


    public ReportResDTO addReport(ReportReqDTO reportRequest) {
        var report = new Report();
        report.setTitle(reportRequest.title());
        report.setDescription(reportRequest.description());
        report.setReportType(reportRequest.reportType());
        reportRepository.saveAndFlush(report);
        return new ReportResDTO(report.getId(), report.getTitle(),
                                report.getCreatedAt());
    }
    public ReportDTO getReportById(long reportId) {
        var report = reportRepository.findById(reportId).orElse(null);
        if(report != null) {
            return reportMapper.reportToReportDTO(report);
        }
        return null;
    }
}
