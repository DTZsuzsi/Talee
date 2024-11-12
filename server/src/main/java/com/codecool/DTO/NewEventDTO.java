package com.codecool.DTO;

import com.codecool.model.tags.Tag;

import java.time.LocalDate;
import java.util.Set;

public record NewEventDTO(LocalDate date, String name, String description, int location_id, Set<String> users, String owner, String size, Set<Tag> tags, String status) {
}
