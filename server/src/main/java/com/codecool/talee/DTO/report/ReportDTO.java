package com.codecool.talee.DTO.report;

import com.codecool.talee.model.reports.ReportType;

import java.time.LocalDateTime;

public record ReportDTO (long id, String title, String description,
                         ReportType type, boolean issued, boolean solved,
                         LocalDateTime createdAt, LocalDateTime updatedAt){
}
