package com.codecool.DTO.report;

import com.codecool.model.reports.ReportType;

public record ReportReqDTO(String title, String description,ReportType reportType) {
}
