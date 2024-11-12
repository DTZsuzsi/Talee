package com.codecool.DTO.tagDTO;

import com.codecool.model.tags.TagCategory;

import java.sql.Timestamp;

public record TagDTO(long id, String name, TagCategory category,Timestamp createdAt) {
}
