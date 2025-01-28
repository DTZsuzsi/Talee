package com.codecool.talee.mapper;

import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.model.events.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    @Mapping(target = "tags", source = "tags")
    EventDTO eventToEventDTO(Event event);
    @Mapping(target = "tags", source = "tags")
    Event eventDTOToEvent(EventDTO eventDTO);



}
