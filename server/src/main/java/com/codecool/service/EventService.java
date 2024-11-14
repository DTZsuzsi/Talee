package com.codecool.service;

import com.codecool.DTO.eventDTO.EventDTO;
import com.codecool.DTO.eventDTO.NewEventDTO;
import com.codecool.DTO.locationDTO.LocationInEvent;
import com.codecool.DTO.tagDTO.TagDTO;
import com.codecool.model.events.Event;
import com.codecool.model.location.Location;
import com.codecool.model.tags.Tag;
import com.codecool.model.tags.TagCategory;
import com.codecool.repository.EventRepository;
import com.codecool.repository.TagCategoryRepository;
import com.codecool.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
//    private final EventsDAO eventsDAO;
    private final EventRepository eventRepository;
    private final TagRepository tagRepository;
    private final TagCategoryRepository tagCategoryRepository;

    @Autowired
    public EventService(EventRepository eventRepository, TagRepository tagRepository, TagCategoryRepository tagCategoryRepository) {
        this.eventRepository = eventRepository;
        this.tagRepository = tagRepository;
        this.tagCategoryRepository = tagCategoryRepository;
    }

    public EventDTO getEventById(int id) {
        Event event = eventRepository.findEventById(id);
        return new EventDTO(event.getId(), event.getDate(), event.getName(), event.getDescription(), getLocationDTOForEvent(event.getLocation()),
                new ArrayList<>(), event.getOwner(), event.getSize(), getTagDTOSet(event), event.getStatus());

    }

    public int addEvent(NewEventDTO newEventDTO) {
        Event newEvent = new Event(newEventDTO.date(), newEventDTO.name(), newEventDTO.description(), newEventDTO.location(),
                newEventDTO.owner(), newEventDTO.size(), newEventDTO.tags(), newEventDTO.status());
        return eventRepository.save(newEvent).getId();
    }

    public boolean modifyEvent(EventDTO eventDTO) {
        Set<Tag> tags = getTagSet(eventDTO.tags());

        Event updatedEvent=new Event(eventDTO.id(), eventDTO.date(), eventDTO.name(), eventDTO.description(),null,
            new HashSet<>(),   eventDTO.owner(), eventDTO.size(),tags,eventDTO.status(),null);
    return eventRepository.save(updatedEvent).getId()>0;
    }

    private static Set<Tag> getTagSet(Collection<TagDTO> tagDTOs) {
        Set <Tag> tags=new HashSet<>();

        for (TagDTO tagDTO: tagDTOs){
            Tag newTag= new Tag(tagDTO.id());
            tags.add(newTag);
        }
        return tags;
    }

    private List<TagDTO> getTagDTOSet(Event event) {
        List<TagDTO> tagDTOSet=new ArrayList<>();
        for (Tag tag: event.getTags()){
            TagDTO tagDTO=new TagDTO(tag.getId(), tag.getName(), tag.getTagCategory().getId(), tag.getCreatedAt());
            tagDTOSet.add(tagDTO);
        }
        return tagDTOSet;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            EventDTO eventDTO=new EventDTO(event.getId(), event.getDate(),event.getName(), event.getDescription(),getLocationDTOForEvent(event.getLocation()),
                    null,event.getOwner(),event.getSize(),getTagDTOSet(event),event.getStatus());
            eventDTOs.add(eventDTO);
        }

        return eventDTOs;
    }




    public boolean deleteEventById(int id){
        return eventRepository.deleteEventById(id);
    }

    public boolean addTagToEvent(int eventId, TagDTO tagDTO) {
        Event event= eventRepository.findEventById(eventId);
        TagCategory tagCategory=tagCategoryRepository.findById(tagDTO.categoryId());
        Tag tag= new Tag(tagDTO.id(), tagDTO.name(), tagCategory, tagDTO.createdAt());
        event.addTag(tag);
        return eventRepository.save(event).getId()>0;
    }

    private LocationInEvent  getLocationDTOForEvent(Location location) {
        return new LocationInEvent(location.getId(), location.getName());
    }

    public boolean deleteTagFromEvent(int eventId, int tagId){
        Event event = eventRepository.findEventById(eventId);
        Set<Tag> tags=event.getTags();
        Set<Tag> updatedTags=tags.stream().filter(tag -> tag.getId()!=tagId).collect(Collectors.toSet());

        event.setTags(updatedTags);
        return eventRepository.save(event).getId()>0;
    }

}
