package com.codecool.DTO.tag;

import java.sql.Timestamp;

public record TagDTO(long id, String name, long categoryId,Timestamp createdAt) {
}
