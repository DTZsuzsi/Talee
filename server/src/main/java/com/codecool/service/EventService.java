package com.codecool.service;

import com.codecool.DTO.EventDTO;
import com.codecool.DTO.NewEventDTO;
import com.codecool.model.events.Event;
import com.codecool.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
//    private final EventsDAO eventsDAO;
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventDTO getEventById(int id) {
        Event event = eventRepository.findEventById(id);
        return new EventDTO(event.getId(), event.getDate(), event.getName(), event.getDescription(), event.getLocation(),
                event.getUsers(), event.getOwner(), event.getSize(), event.getTags(), event.getStatus(), event.getTimestamp());

    }

    public int addEvent(NewEventDTO newEventDTO) {
        Event newEvent = new Event(newEventDTO.date(), newEventDTO.name(), newEventDTO.description(), newEventDTO.location(),
                newEventDTO.owner(), newEventDTO.size(), newEventDTO.tags(), newEventDTO.status());
        return eventRepository.save(newEvent).getId();
    }

    public boolean modifyEvent(EventDTO eventDTO) {
        Event updatedEvent=new Event(eventDTO.id(), eventDTO.date(), eventDTO.name(), eventDTO.description(),eventDTO.location(),
             eventDTO.users(),   eventDTO.owner(), eventDTO.size(),eventDTO.tags(),eventDTO.status(),eventDTO.timestamp());
    return eventRepository.save(updatedEvent).getId()>0;
    }
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            EventDTO eventDTO=new EventDTO(event.getId(), event.getDate(),event.getName(), event.getDescription(),event.getLocation(),
                    null,event.getOwner(),event.getSize(),null,event.getStatus(),event.getTimestamp());
            eventDTOs.add(eventDTO);
        }
        return eventDTOs;
    }

    public boolean deleteEventById(int id){
        return eventRepository.deleteEventById(id);
    }
}
