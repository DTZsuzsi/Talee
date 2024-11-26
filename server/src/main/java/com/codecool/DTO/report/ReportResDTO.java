package com.codecool.DTO.report;

import java.time.LocalDateTime;

public record ReportResDTO(long id, String title, LocalDateTime createdAt) {
}
