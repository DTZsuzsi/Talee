package com.codecool.talee.service;

import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.DTO.event.NewEventDTO;
import com.codecool.talee.DTO.location.LocationInEventDTO;
import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.DTO.user.UserInEventDTO;
import com.codecool.talee.exception.EventNotFoundException;
import com.codecool.talee.exception.LocationNotFoundException;
import com.codecool.talee.exception.TagNotFoundException;
import com.codecool.talee.exception.UserNotFoundException;
import com.codecool.talee.mapper.EventMapper;
import com.codecool.talee.mapper.TagMapper;
import com.codecool.talee.mapper.UserMapper;
import com.codecool.talee.model.events.Event;
import com.codecool.talee.model.locations.Location;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.users.UserEntity;
import com.codecool.talee.repository.*;
import com.codecool.talee.security.JWTUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;
    private final TagMapper tagMapper = TagMapper.INSTANCE;
    private final LocationRepository locationRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;


    @Autowired
    public EventService(EventRepository eventRepository, TagRepository tagRepository, LocationRepository locationRepository, UserRepository userRepository, JWTUtils jwtUtils) {
        this.eventRepository = eventRepository;
        this.tagRepository = tagRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }


    public EventDTO getEventById(long id) {
        Event event = eventRepository.findEventById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        Set<TagDTO> tags = event.getTags().stream()
                .map(tagMapper::tagToTagDTO)
                .collect(Collectors.toSet());

        List<UserInEventDTO> users = event.getUsers().stream()
                .map(userEntity -> new UserInEventDTO(userEntity.getId(), userEntity.getUsername()))
                .collect(Collectors.toList());

        Location location = event.getLocation();
        LocationInEventDTO locationInEventDTO = new LocationInEventDTO(
                location.getId(), location.getName(), location.getLatitude(), location.getLongitude());

        return new EventDTO(
                event.getId(), event.getDate(), event.getName(), event.getDescription(),
                locationInEventDTO, users, userMapper.userToUserInEventDTO(event.getOwner()),
                event.getSize(), tags, event.getStatus());
    }

    public long addEvent(NewEventDTO newEventDTO) {
        Location location = locationRepository.findById(newEventDTO.locationInEventDTO().locationId())
                .orElseThrow(() -> new LocationNotFoundException(newEventDTO.locationInEventDTO().locationId()));

        UserEntity eventOwner = userRepository.findById(newEventDTO.owner().id())
                .orElseThrow(() -> new UserNotFoundException(newEventDTO.owner().id()));

        Event newEvent = new Event(
                newEventDTO.date(), newEventDTO.name(), newEventDTO.description(), location,
                eventOwner, newEventDTO.size(), null, newEventDTO.status());

        Set<UserEntity> users = Set.of(eventOwner);
        newEvent.setUsers(users);

        return eventRepository.save(newEvent).getId();
    }


    public boolean modifyEvent(EventDTO eventDTO) {
        Event updatedEvent = eventMapper.eventDTOToEvent(eventDTO);
        return eventRepository.save(updatedEvent).getId() > 0;
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::eventToEventDTO)
                .collect(Collectors.toList());
    }

    public List<EventDTO> getEventsByTag(String tagName) {
        Tag tag = tagRepository.findByName(tagName)
                .orElseThrow(() -> new TagNotFoundException(tagName));

        return eventRepository.findEventsByTagsContaining(tag).stream()
                .map(eventMapper::eventToEventDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteEventById(long id) {
        eventRepository.deleteEventById(id);
    }

    public boolean applyUserToEvent(long eventId, String token) {
        String currentUserName = jwtUtils.getUsernameFromJwtToken(token);

        UserEntity currentUser = userRepository.findByUsername(currentUserName)
                .orElseThrow(() -> new UserNotFoundException(currentUserName));

        Event event = eventRepository.findEventById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        Set<UserEntity> users = event.getUsers();
        if (users.contains(currentUser)) return false;

        users.add(currentUser);
        return eventRepository.save(event).getId() > 0;
    }

    public boolean deleteUserFromEvent(long eventId, long userId) {
        Event event = eventRepository.findEventById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));

        Set<UserEntity> updatedUsers = event.getUsers().stream()
                .filter(user -> user.getId() != userId)
                .collect(Collectors.toSet());

        event.setUsers(updatedUsers);
        return eventRepository.save(event).getId() > 0;
    }

    public List<EventDTO> findAllEventsByLocationId(long locationId) {
        return eventRepository.findAllByLocationId(locationId).stream()
                .map(eventMapper::eventToEventDTO)
                .collect(Collectors.toList());
    }
}
