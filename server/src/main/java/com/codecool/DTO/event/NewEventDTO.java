package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.user.UserDTO;
import com.codecool.model.tags.Tag;
import com.codecool.model.locations.Location;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.Set;

public record NewEventDTO(LocalDate date, String name, String description, @JsonManagedReference LocationDTO location, @JsonManagedReference Set<UserDTO> users, String owner, String size, Set<Tag> tags, String status) {
}
