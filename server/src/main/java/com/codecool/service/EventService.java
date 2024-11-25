package com.codecool.service;

import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.event.NewEventDTO;
import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.tag.TaginEventDTO;
import com.codecool.mapper.EventMapper;
import com.codecool.mapper.TagMapper;
import com.codecool.model.events.Event;
import com.codecool.model.locations.Location;
import com.codecool.model.tags.Tag;
import com.codecool.model.tags.TagCategory;
import com.codecool.repository.EventRepository;
import com.codecool.repository.TagCategoryRepository;
import com.codecool.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    @Autowired
    public EventService(EventRepository eventRepository, TagRepository tagRepository, TagCategoryRepository tagCategoryRepository) {
        this.eventRepository = eventRepository;
        this.tagRepository = tagRepository;
        this.tagCategoryRepository = tagCategoryRepository;

    }

    private List<Tag> getTagSet(Collection<TaginEventDTO> taginEventDTOS) {
        return taginEventDTOS.stream().map(tagMapper::tagInEventDTOsToTag).toList();
    }

    public EventDTO getEventById(long id) {
        Event event = eventRepository.findEventById(id);
        return eventMapper.eventToEventDTO(event);

    }

    public long addEvent(NewEventDTO newEventDTO) {
        Event newEvent = eventMapper.newEventDTOToEvent(newEventDTO);
        return eventRepository.save(newEvent).getId();
    }

    public boolean modifyEvent(EventDTO eventDTO) {
        Event updatedEvent = eventMapper.eventDTOToEvent(eventDTO);
        return eventRepository.save(updatedEvent).getId() > 0;
    }

    private List<TaginEventDTO> getTagDTOSet(Event event) {
        return event.getTags().stream().map(tagMapper::tagToTaginEventDTO).collect(Collectors.toList());
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::eventToEventDTO)
                .toList();

    }

    public boolean deleteEventById(long id) {
        return eventRepository.deleteEventById(id);
    }

    public boolean addTagToEvent(long eventId, TaginEventDTO taginEventDTO) {
        Event event = eventRepository.findEventById(eventId);
        TagCategory tagCategory = tagCategoryRepository.findById(taginEventDTO.categoryId());
        Tag tag = new Tag(taginEventDTO.id(), taginEventDTO.name(), tagCategory, null);
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

}
