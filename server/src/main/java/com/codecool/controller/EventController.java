package com.codecool.controller;

import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.event.NewEventDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")

public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{eventId}")
    public EventDTO getEventById(@PathVariable int eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping
    public long createEvent(@RequestBody NewEventDTO newEventDTO) {
        return eventService.addEvent(newEventDTO);
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PatchMapping("/{eventId}/modify")
    public boolean modifyEvent(@PathVariable int eventId, @RequestBody EventDTO eventDTO) {
        return eventService.modifyEvent(eventDTO);
    }

    @DeleteMapping("/{eventId}")
    public boolean deleteEvent(@PathVariable long eventId) {
        return eventService.deleteEventById(eventId);
    }

    @PostMapping("/{eventId}")
    public boolean addTagToEvent(@PathVariable long eventId, @RequestBody TaginFrontendDTO taginFrontendDTO) {
        return eventService.addTagToEvent(eventId, taginFrontendDTO);
    }

    @DeleteMapping("/tag/{eventId}")
    public boolean deleteTag(@PathVariable long eventId, @RequestParam int tagId) {
        return eventService.deleteTagFromEvent(eventId, tagId);
    }

    @GetMapping("/locations/{locationId}")
    public List<EventDTO> getEventsByLocation(@PathVariable long locationId) {
        return eventService.findAllByLocationId(locationId);
    }
}
