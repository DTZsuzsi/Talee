package com.codecool.talee.controller;

import com.codecool.talee.DTO.report.ReportDTO;
import com.codecool.talee.DTO.report.ReportReqDTO;
import com.codecool.talee.DTO.report.ReportResDTO;
import com.codecool.talee.model.reports.ReportType;
import com.codecool.talee.service.ReportService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "user", roles = {"USER"})
public class ReportControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    String newReport = """
            {
                "type": "BUG",
                "description": "Sample report description",
                "createdBy": "user1",
                "relatedEventId": 1
            }
            """;

    @BeforeAll
    void setUpOnce() {
        when(reportService.getAllReports(null, null, null, "createdAt", "asc"))
                .thenReturn(Collections.emptyList());
    }

    @Test
    void getAllReports_returns200AndEmptyList() throws Exception {
        mockMvc.perform(get("/api/reports"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getReportById_returns200() throws Exception {
        ReportDTO mockReport = new ReportDTO(1L, "BUG", "Sample report description", ReportType.BUG, true, false, LocalDateTime.now(), LocalDateTime.now());
        when(reportService.getReportById(1L)).thenReturn(mockReport);

        mockMvc.perform(get("/api/reports/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type").value("BUG"));
    }

    @Test
    void addReport_returns201() throws Exception {
        ReportResDTO mockResponse = new ReportResDTO(1L, "Report successfully created", LocalDateTime.now());
        when(reportService.addReport(any(ReportReqDTO.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/reports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReport))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Report successfully created"));
    }
}
