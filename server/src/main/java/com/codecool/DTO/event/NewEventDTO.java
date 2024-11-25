package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.tag.TaginEventDTO;
import com.codecool.DTO.user.UserInEventDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.Set;

public record NewEventDTO(LocalDate date, String name, String description, @JsonManagedReference LocationInEventDTO locationInEventDTO, @JsonManagedReference Set<UserInEventDTO> users, String owner, String size, Set<TaginEventDTO> tags, String status) {
}
