package com.codecool.DTO.location;


import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.DTO.user.UserDTO;

import java.util.List;
import java.util.Set;

public record LocationDTO(long id, String name, String address, String phone, String email, String description, UserDTO adminUser, List<OpeningHoursDTO> openingHours, Set<TaginFrontendDTO> tags, double latitude, double longitude, Set<EventDTO> events) {
}
