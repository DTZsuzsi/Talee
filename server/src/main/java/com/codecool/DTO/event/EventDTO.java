package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.DTO.user.UserInEventDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public record EventDTO(
        long id,
        LocalDate date,
        String name,
        String description,
        LocationInEventDTO location,
        List<UserInEventDTO> users,
        UserInEventDTO owner,
        String size,
        Set<TaginFrontendDTO> tags,
        String status) {
}