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
    public EventDTO getEventById(@PathVariable long eventId) {
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
    public long modifyEvent( @RequestBody EventDTO eventDTO) {
        return eventService.modifyEvent(eventDTO);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public boolean deleteEvent(@PathVariable long eventId) {
        return eventService.deleteEventById(eventId);
    }


    @PostMapping("/apply/{eventId}")
    public ResponseEntity<String> userApplyToEvent(@PathVariable long eventId, @RequestHeader (name = "Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing Authorization header");
        }

        String jwtToken = token.substring(7);
        boolean success = eventService.applyUserToEvent(eventId, jwtToken);

        return ResponseEntity.ok(success ? "true" : "false");
    }

    @DeleteMapping("/user/{eventId}")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public boolean deleteUser(@PathVariable long eventId, @RequestParam long userId) {
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
