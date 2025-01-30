package com.codecool.talee.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.codecool.talee.DTO.report.ReportDTO;
import com.codecool.talee.DTO.report.ReportReqDTO;
import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.mapper.ReportMapper;
import com.codecool.talee.model.reports.Report;
import com.codecool.talee.model.reports.ReportType;
import com.codecool.talee.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportMapper reportMapper;

    @InjectMocks
    private ReportService reportService;

    private Report mockReport;
    private ReportDTO mockReportDTO;
    private ReportReqDTO mockReportReqDTO;

    private static final long TEST_REPORT_ID = 1L;

    @BeforeEach
    void setUp() {
        mockReport = new Report("Test Title", "Test Description", ReportType.BUG);
        mockReportDTO = new ReportDTO(TEST_REPORT_ID, "Test Title", "Test Description", ReportType.BUG, false, false, LocalDateTime.now(), LocalDateTime.now());
        mockReportReqDTO = new ReportReqDTO("Test Title", "Test Description", ReportType.BUG);
    }

    @Nested
    class GetReportsTests {
        @Test
        void getAllReports_ShouldReturnListOfReports() {
            when(reportRepository.findAll()).thenReturn(List.of(mockReport));
            when(reportMapper.reportToReportDTO(mockReport)).thenReturn(mockReportDTO);

            List<ReportDTO> reports = reportService.getAllReports();

            assertEquals(1, reports.size());
            assertEquals("Test Title", reports.get(0).title());
        }

        @Test
        void getReportById_ShouldReturnReport_WhenReportExists() {
            when(reportRepository.findById(TEST_REPORT_ID)).thenReturn(Optional.of(mockReport));
            when(reportMapper.reportToReportDTO(mockReport)).thenReturn(mockReportDTO);

            ReportDTO reportDTO = reportService.getReportById(TEST_REPORT_ID);

            assertNotNull(reportDTO);
            assertEquals(TEST_REPORT_ID, reportDTO.id());
        }

        @Test
        void getReportById_ShouldThrowException_WhenReportNotFound() {
            when(reportRepository.findById(TEST_REPORT_ID)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> reportService.getReportById(TEST_REPORT_ID));
        }
    }

    @Nested
    class CreateReportTests {
        @Test
        void createReport_ShouldSaveAndReturnReport() {
            when(reportRepository.saveAndFlush(any(Report.class))).thenReturn(mockReport);
            when(reportMapper.reportToReportDTO(mockReport)).thenReturn(mockReportDTO);

            ReportDTO createdReport = reportService.createReport(mockReportReqDTO);

            assertNotNull(createdReport);
            assertEquals("Test Title", createdReport.title());
        }
    }
}
