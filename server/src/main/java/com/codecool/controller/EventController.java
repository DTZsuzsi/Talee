package com.codecool.controller;

import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.event.NewEventDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.DTO.user.UserInEventDTO;
import com.codecool.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public long createEvent(@RequestBody NewEventDTO newEventDTO) {
        return eventService.addEvent(newEventDTO);
    }

    @GetMapping("/all")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PatchMapping("/{eventId}/modify")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public boolean modifyEvent(@PathVariable int eventId, @RequestBody EventDTO eventDTO) {
        return eventService.modifyEvent(eventDTO);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public void deleteEvent(@PathVariable long eventId) {
        eventService.deleteEventById(eventId);
    }

    @PostMapping("/tag/{eventId}")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public boolean addTagToEvent(@PathVariable long eventId, @RequestBody TaginFrontendDTO taginFrontendDTO) {
        return eventService.addTagToEvent(eventId, taginFrontendDTO);
    }

    @DeleteMapping("/tag/{eventId}")
    @PreAuthorize("hasRole('EVENT_OWNER')")
    public boolean deleteTag(@PathVariable long eventId, @RequestParam int tagId) {
        return eventService.deleteTagFromEvent(eventId, tagId);
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
        return eventService.findAllByLocationId(locationId);
    }

    @GetMapping("/tagsfilter/{tagName}")
    public List<EventDTO> getEventsByTag(@PathVariable String tagName) {
        return eventService.getEventsByTag(tagName);
    }
}
