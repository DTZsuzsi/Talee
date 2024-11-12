package com.codecool.controller;

import com.codecool.DTO.EventDTO;
import com.codecool.DTO.NewEventDTO;
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

    @PostMapping("")
    public int createEvent(@RequestBody NewEventDTO newEventDTO) {
       return eventService.addEvent(newEventDTO);
    }

    @GetMapping("")
    public List<EventDTO> getAllEvents() {
       return eventService.getAllEvents();
    }

    @PatchMapping("/{eventId}/modify")
    public boolean modifyEvent(@PathVariable int eventId, @RequestBody EventDTO eventDTO) {
       return eventService.modifyEvent(eventDTO);
    }

    @DeleteMapping("/{eventId}")
    public boolean deleteEvent(@PathVariable int eventId) {
       return eventService.deleteEventById(eventId);
    }



}
