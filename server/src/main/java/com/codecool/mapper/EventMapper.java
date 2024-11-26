package com.codecool.mapper;

import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.event.NewEventDTO;
import com.codecool.model.events.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper//(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
    @Mapping(target = "tags", source = "tags")
    EventDTO eventToEventDTO(Event event);
    @Mapping(target = "tags", source = "tags")
    Event eventDTOToEvent(EventDTO eventDTO);
    Event newEventDTOToEvent(NewEventDTO newEventDTO);
}
