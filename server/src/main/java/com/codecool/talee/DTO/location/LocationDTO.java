package com.codecool.talee.DTO.location;


import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.DTO.user.UserDTO;

import java.util.List;
import java.util.Set;

public record LocationDTO(long id, String name, String address, String phone, String email, String description, UserDTO adminUser, List<OpeningHoursDTO> openingHours, Set<TagDTO> tags, double latitude, double longitude, Set<EventDTO> events) {
}
