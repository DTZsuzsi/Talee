package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.tag.TaginEventDTO;
import com.codecool.DTO.user.UserInEventDTO;

import java.time.LocalDate;
import java.util.List;


public record EventDTO(long id, LocalDate date, String name, String description, LocationInEventDTO location, List<UserInEventDTO> users, String owner, String size, List<TaginEventDTO> tags, String status) {
}