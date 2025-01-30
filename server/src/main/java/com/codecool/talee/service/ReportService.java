package com.codecool.talee.service;
import com.codecool.talee.DTO.report.ReportDTO;
import com.codecool.talee.DTO.report.ReportReqDTO;
import com.codecool.talee.DTO.report.ReportResDTO;
import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.mapper.ReportMapper;
import com.codecool.talee.model.reports.Report;
import com.codecool.talee.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    @Autowired
    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::reportToReportDTO)
                .toList();
    }

    public ReportDTO getReportById(long id) {
        return reportRepository.findById(id)
                .map(reportMapper::reportToReportDTO)
                .orElseThrow(() -> new EntityNotFoundException("Report", id));
    }

    public ReportDTO createReport(ReportReqDTO reportRequest) {
        Report report = new Report(
                reportRequest.title(),
                reportRequest.description(),
                reportRequest.reportType()
        );

        Report savedReport = reportRepository.saveAndFlush(report);
        return reportMapper.reportToReportDTO(savedReport);
    }
}
