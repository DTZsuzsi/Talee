package com.codecool.service;

import com.codecool.DAO.eventsDAO.EventsDAO;
import com.codecool.DTO.EventDTO;
import com.codecool.DTO.NewEventDTO;
import com.codecool.model.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventsDAO eventsDAO;

    @Autowired
    public EventService(EventsDAO eventsDAO) {
        this.eventsDAO = eventsDAO;
    }

    public EventDTO getEventById(int id) {
        Event event = eventsDAO.getEventById(id);
        return new EventDTO(event.getId(), event.getDate(), event.getName(), event.getDescription(), event.getLocation_id(),
                event.getUsers(), event.getOwner(), event.getSize(), event.getTags(), event.getStatus(), event.getTimestamp());

    }

    public int addEvent(NewEventDTO newEventDTO) {
        Event newEvent = new Event(newEventDTO.date(), newEventDTO.name(), newEventDTO.description(), newEventDTO.location_id(),
                newEventDTO.owner(), newEventDTO.size(), newEventDTO.tags(), newEventDTO.status());
        return eventsDAO.createEvent(newEvent);
    }

    public boolean modifyEvent(EventDTO eventDTO) {
        Event updatedEvent=new Event(eventDTO.id(), eventDTO.date(), eventDTO.name(), eventDTO.description(),eventDTO.location_id(),
             eventDTO.users(),   eventDTO.owner(), eventDTO.size(),eventDTO.tags(),eventDTO.status(),eventDTO.timestamp());
    return eventsDAO.modifyEvent(updatedEvent);
    }
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventsDAO.getAllEvents();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            EventDTO eventDTO=new EventDTO(event.getId(), event.getDate(),event.getName(), event.getDescription(),event.getLocation_id(),
                    event.getUsers(),event.getOwner(),event.getSize(),event.getTags(),event.getStatus(),event.getTimestamp());
            eventDTOs.add(eventDTO);
        }
        return eventDTOs;
    }

    public boolean deleteEventById(int id){
        return eventsDAO.deleteEvent(id);
    }
}
