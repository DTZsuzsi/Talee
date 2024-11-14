package com.codecool.service;

import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.event.NewEventDTO;
import com.codecool.mapper.EventMapper;
import com.codecool.mapper.UserMapper;
import com.codecool.model.events.Event;
import com.codecool.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventDTO getEventById(int id) {
        Event event = eventRepository.findEventById(id);
        return eventMapper.eventToEventDTO(event);
    }

    public int addEvent(NewEventDTO newEventDTO) {
        Event newEvent = eventMapper.newEventToEvent(newEventDTO);
        return eventRepository.save(newEvent).getId();
    }

    public boolean modifyEvent(EventDTO eventDTO) {
        Event updatedEvent = eventMapper.eventDTOToEvent(eventDTO);
        return eventRepository.save(updatedEvent).getId() > 0;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::eventToEventDTO)
                .toList();
    }

    public boolean deleteEventById(int id){
        return eventRepository.deleteEventById(id);
    }
}
