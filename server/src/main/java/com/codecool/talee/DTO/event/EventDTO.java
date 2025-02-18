package com.codecool.talee.DTO.event;

import com.codecool.talee.DTO.location.LocationInEventDTO;
import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.DTO.user.UserInEventDTO;
import com.codecool.talee.model.events.EventSize;

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
        EventSize size,
        Set<TagDTO> tags,
        String status) {
}