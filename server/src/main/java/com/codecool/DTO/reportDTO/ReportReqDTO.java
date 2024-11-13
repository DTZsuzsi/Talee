package com.codecool.DTO.reportDTO;

import com.codecool.model.reports.ReportType;

public record ReportReqDTO(String title, String description,ReportType type) {
}
