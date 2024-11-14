package com.codecool.DTO.reportDTO;

import java.time.LocalDateTime;

public record ReportResDTO(long id, String title, LocalDateTime createdAt) {
}
