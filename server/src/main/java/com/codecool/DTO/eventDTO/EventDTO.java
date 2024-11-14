package com.codecool.DTO.eventDTO;

import com.codecool.DTO.locationDTO.LocationInEvent;
import com.codecool.DTO.tagDTO.TagDTO;
import com.codecool.model.location.Location;
import com.codecool.model.users.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public record EventDTO(int id, LocalDate date, String name, String description, LocationInEvent location, List<User> users, String owner, String size, List<TagDTO> tags, String status) {
}
