package com.codecool.service;

import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.event.NewEventDTO;
import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.mapper.EventMapper;
import com.codecool.mapper.LocationMapper;
import com.codecool.mapper.TagMapper;
import com.codecool.model.events.Event;
import com.codecool.model.locations.Location;
import com.codecool.model.tags.Tag;
import com.codecool.model.tags.TagCategory;
import com.codecool.repository.EventRepository;
import com.codecool.repository.LocationRepository;
import com.codecool.repository.TagCategoryRepository;
import com.codecool.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {
    //    private final EventsDAO eventsDAO;
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;
    private final TagCategoryRepository tagCategoryRepository;
    private final EventMapper eventMapper = EventMapper.INSTANCE;
    private final TagMapper tagMapper = TagMapper.INSTANCE;
    private final LocationMapper locationMapper = LocationMapper.INSTANCE;
    private final LocationRepository locationRepository;

    @Autowired
    public EventService(EventRepository eventRepository, TagRepository tagRepository, TagCategoryRepository tagCategoryRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.tagRepository = tagRepository;
        this.tagCategoryRepository = tagCategoryRepository;
        this.locationRepository = locationRepository;
    }


    public EventDTO getEventById(long id) {
        Event event = eventRepository.findEventById(id);
        Set<TaginFrontendDTO> tags = event.getTags().stream().map(tagMapper::tagToTaginFrontendDTO).collect(Collectors.toSet());
        EventDTO eventDTO = new EventDTO(event.getId(), event.getDate(), event.getName(), event.getDescription(), new LocationInEventDTO(event.getLocation().getId(), event.getLocation().getName()),
                null, event.getOwner(), event.getSize(), tags, event.getStatus());

        return eventDTO;

    }

    public long addEvent(NewEventDTO newEventDTO) {
        Location location = locationRepository.findById(newEventDTO.locationInEventDTO().locationId()).get();
        Event newEvent = new Event(newEventDTO.date(), newEventDTO.name(), newEventDTO.description(), location,
                newEventDTO.owner(), newEventDTO.size(), null, newEventDTO.status());
        return eventRepository.save(newEvent).getId();
    }

    public boolean modifyEvent(EventDTO eventDTO) {
        Event updatedEvent = eventMapper.eventDTOToEvent(eventDTO);
        return eventRepository.save(updatedEvent).getId() > 0;
    }

    private Set<TaginFrontendDTO> getTagDTOSet(Event event) {
        return event.getTags().stream().map(tagMapper::tagToTaginFrontendDTO).collect(Collectors.toSet());
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDTOS = events.stream().map(event -> getEventById(event.getId())).collect(Collectors.toList());
        return eventDTOS;
    }

    @Transactional
    public void deleteEventById(long id) {
        eventRepository.deleteEventById(id);
    }


    @Transactional
    public boolean addTagToEvent(long eventId, TaginFrontendDTO taginFrontendDTO) {
        Event event = eventRepository.findEventById(eventId);
        TagCategory tagCategory = tagCategoryRepository.findById(taginFrontendDTO.categoryId());
        Tag tag = new Tag(taginFrontendDTO.id(), taginFrontendDTO.name(), tagCategory);
        event.addTag(tag);
        return eventRepository.save(event).getId() > 0;
    }


    private LocationInEventDTO getLocationDTOForEvent(Location location) {
        return new LocationInEventDTO(location.getId(), location.getName());
    }

    public boolean deleteTagFromEvent(long eventId, long tagId) {
        Event event = eventRepository.findEventById(eventId);
        Set<Tag> tags = event.getTags();
        Set<Tag> updatedTags = tags.stream().filter(tag -> tag.getId() != tagId).collect(Collectors.toSet());

        event.setTags(updatedTags);
        return eventRepository.save(event).getId() > 0;
    }

    public List<EventDTO> findAllByLocationId(long locationId) {
        List<Event> events = eventRepository.findAllByLocationId(locationId);
        return events.stream().map(eventMapper::eventToEventDTO).collect(Collectors.toList());
    }

}
