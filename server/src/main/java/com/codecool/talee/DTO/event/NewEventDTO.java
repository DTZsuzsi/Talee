package com.codecool.talee.DTO.event;

import com.codecool.talee.DTO.location.LocationInEventDTO;
import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.DTO.user.UserInEventDTO;

import java.time.LocalDate;
import java.util.Set;

public record NewEventDTO(LocalDate date, String name, String description, LocationInEventDTO locationInEventDTO, Set<UserInEventDTO> users, UserInEventDTO owner, String size, Set<TagDTO> tags, String status) {
}
