package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.tag.TagInFrontendDTO;
import com.codecool.DTO.user.UserInEventDTO;

import java.time.LocalDate;
import java.util.Set;

public record NewEventDTO(LocalDate date, String name, String description, LocationInEventDTO locationInEventDTO, Set<UserInEventDTO> users, UserInEventDTO owner, String size, Set<TagInFrontendDTO> tags, String status) {
}
