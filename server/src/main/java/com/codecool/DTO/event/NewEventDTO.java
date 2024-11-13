package com.codecool.DTO.event;

import com.codecool.model.tags.Tag;
import com.codecool.model.locations.Location;
import java.time.LocalDate;
import java.util.Set;

public record NewEventDTO(LocalDate date, String name, String description, Location location, Set<String> users, String owner, String size, Set<Tag> tags, String status) {
}
