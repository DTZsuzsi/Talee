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
        final String sortField = sortBy != null ? sortBy : "id";

        return reportRepository.findAll().stream()
                               .filter(report -> (reportType == null || report.getReportType() == reportType))
                               .filter(report -> (issued == null || report.isIssued() == issued))
                               .filter(report -> (solved == null || report.isSolved() == solved))
                               .sorted((report1, report2) -> {
                                   if ("createdAt".equals(sortField)) {
                                       return direction == Sort.Direction.DESC
                                               ? report2.getCreatedAt().compareTo(report1.getCreatedAt())
                                               : report1.getCreatedAt().compareTo(report2.getCreatedAt());
                                   } else if ("title".equals(sortField)) {
                                       return direction == Sort.Direction.DESC
                                               ? report2.getTitle().compareTo(report1.getTitle())
                                               : report1.getTitle().compareTo(report2.getTitle());
                                   }
                                   return 0;
                               })
                               .map(report -> reportMapper.reportToReportDTO(report))
                               .collect(Collectors.toList());
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
