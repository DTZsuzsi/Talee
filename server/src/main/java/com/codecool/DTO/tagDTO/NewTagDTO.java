package com.codecool.DTO.tagDTO;

import com.codecool.model.tags.TagCategory;

import java.sql.Timestamp;

public record NewTagDTO(String name, TagCategory category) {
}
