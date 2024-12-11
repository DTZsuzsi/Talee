package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.DTO.user.UserInEventDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.util.Set;

public record NewEventDTO(LocalDate date, String name, String description,  LocationInEventDTO locationInEventDTO,  Set<UserInEventDTO> users, UserInEventDTO owner, String size, Set<TaginFrontendDTO> tags, String status) {
}
