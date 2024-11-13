package com.codecool.DTO.event;

import com.codecool.DTO.user.UserDTO;
import com.codecool.model.tags.Tag;
import com.codecool.model.locations.Location;
import com.codecool.model.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

public record EventDTO(int id, LocalDate date, String name, String description, @JsonIgnore Location location, @JsonIgnore Set<User> users, String owner, String size,  @JsonIgnore Set<Tag> tags, String status, Timestamp timestamp) {
}
