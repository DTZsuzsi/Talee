package com.codecool.talee.controller;

import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.DTO.event.NewEventDTO;
import com.codecool.talee.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Long> createEvent(@RequestBody NewEventDTO newEventDTO) {
        return new ResponseEntity<>(eventService.addEvent(newEventDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PatchMapping("/modify")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public boolean modifyEvent( @RequestBody EventDTO eventDTO) {
        return eventService.modifyEvent(eventDTO);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public void deleteEvent(@PathVariable long eventId) {
        eventService.deleteEventById(eventId);
    }


    @PostMapping("/apply/{eventId}")
    public boolean userApplyToEvent(@PathVariable long eventId, @RequestHeader (name = "Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return eventService.applyUserToEvent(eventId, token);
    }

    @DeleteMapping("/user/{eventId}")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public boolean deleteUser(@PathVariable long eventId, @RequestParam int userId) {
        return eventService.deleteUserFromEvent(eventId, userId);
    }

    @GetMapping("/locations/{locationId}")
    public List<EventDTO> getEventsByLocation(@PathVariable long locationId) {
        return eventService.findAllEventsByLocationId(locationId);
    }

    @GetMapping("/tagsfilter/{tagName}")
    public List<EventDTO> getEventsByTag(@PathVariable String tagName) {
        return eventService.getEventsByTag(tagName);
    }
}
