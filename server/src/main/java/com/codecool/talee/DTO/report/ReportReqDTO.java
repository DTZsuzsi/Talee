package com.codecool.talee.DTO.report;

import com.codecool.talee.model.reports.ReportType;

public record ReportReqDTO(String title, String description, ReportType reportType) {
}
