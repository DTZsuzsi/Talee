package com.codecool.DTO.tagDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

public record TagCategoryDTO(long id, String name, String color, Timestamp createdAt) {
}
