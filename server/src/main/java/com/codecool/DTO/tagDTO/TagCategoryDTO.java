package com.codecool.DTO.tagDTO;

import java.sql.Timestamp;

public record TagCategoryDTO(long id, String name, String color, Timestamp createdAt) {
}
