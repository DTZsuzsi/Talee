package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.user.UserDTO;
import com.codecool.model.tags.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

public record EventDTO(int id, LocalDate date, String name, String description, @JsonIgnore LocationDTO location, @JsonIgnore Set<UserDTO> users, String owner, String size, @JsonIgnore Set<Tag> tags, String status, Timestamp timestamp) {
}
