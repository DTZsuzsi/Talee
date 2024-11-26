package com.codecool.DTO.report;

import com.codecool.model.reports.ReportType;

import java.time.LocalDateTime;

public record ReportDTO (long id, String title, String description,
                         ReportType type, boolean issued, boolean solved,
                         LocalDateTime createdAt, LocalDateTime updatedAt){
}
