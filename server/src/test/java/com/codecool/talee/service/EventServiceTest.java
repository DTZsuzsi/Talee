package com.codecool.talee.service;

import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.DTO.event.NewEventDTO;
import com.codecool.talee.DTO.location.LocationInEventDTO;
import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.DTO.user.UserInEventDTO;
import com.codecool.talee.mapper.EventMapper;
import com.codecool.talee.model.events.Event;
import com.codecool.talee.model.events.EventSize;
import com.codecool.talee.model.locations.Location;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.users.UserEntity;
import com.codecool.talee.repository.EventRepository;
import com.codecool.talee.repository.LocationRepository;
import com.codecool.talee.repository.TagRepository;
import com.codecool.talee.repository.UserRepository;
import com.codecool.talee.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private EventMapper eventMapper;

    @InjectMocks
    private EventService eventService;

    private static final long TEST_EVENT_ID = 1L;
    private static final String TEST_USERNAME = "testuser";

    private Event mockEvent;
    private NewEventDTO newEventDTO;

    @BeforeEach
    void setUp() {
        Location location = createMockLocation(1L, "Test Location");
        UserEntity owner = createMockUser(1L, TEST_USERNAME);
        Tag tag = createMockTag(1L, "Test Tag");

        mockEvent = createMockEvent(TEST_EVENT_ID, "Test Event", "Test Description", location, Set.of(owner), owner, Set.of(tag));

        newEventDTO = new NewEventDTO(
                LocalDate.now(),
                "Test Event",
                "Test Description",
                new LocationInEventDTO(1L, "Test Location", 40.7128, -74.0060),
                new UserInEventDTO(1L, TEST_USERNAME),
                EventSize.SMALL,
                new HashSet<>(),
                "ACTIVE"
        );
    }

    @Nested
    class GetEventTests {

        @Test
        void getEventByIdTest() {
            EventDTO mockEventDTO = new EventDTO(
                    TEST_EVENT_ID, LocalDate.now(), "Test Event", "Test Description",
                    new LocationInEventDTO(1L, "Test Location", 40.7128, -74.0060),
                    List.of(new UserInEventDTO(1L, TEST_USERNAME)),
                    new UserInEventDTO(1L, TEST_USERNAME), EventSize.SMALL,
                    Set.of(new TagDTO(1L, "Test Tag", 1, "black")), "Active"
            );

            when(eventRepository.findEventById(TEST_EVENT_ID)).thenReturn(Optional.of(mockEvent));
            when(eventMapper.eventToEventDTO(mockEvent)).thenReturn(mockEventDTO);

            EventDTO eventDTO = eventService.getEventById(TEST_EVENT_ID);

            assertNotNull(eventDTO);
            assertEquals(TEST_EVENT_ID, eventDTO.id());
            assertEquals("Test Event", eventDTO.name());
        }

        @Test
        void getAllEventsTest() {
            Event event1 = createMockEvent(1L, "Event 1", "Description", null, null, null, null);
            Event event2 = createMockEvent(2L, "Event 2", "Description", null, null, null, null);

            EventDTO eventDTO1 = new EventDTO(1L, LocalDate.now(), "Event 1", "Description", null, List.of(), null, EventSize.SMALL, Set.of(), "ACTIVE");
            EventDTO eventDTO2 = new EventDTO(2L, LocalDate.now(), "Event 2", "Description", null, List.of(), null, EventSize.SMALL, Set.of(), "ACTIVE");

            when(eventRepository.findAll()).thenReturn(List.of(event1, event2));
            when(eventMapper.eventToEventDTO(event1)).thenReturn(eventDTO1);
            when(eventMapper.eventToEventDTO(event2)).thenReturn(eventDTO2);

            List<EventDTO> events = eventService.getAllEvents();

            assertEquals(2, events.size());
        }
    }

    @Nested
    class ModifyEventTests {

        @Test
        void addEventTest() {
            Location location = createMockLocation(1L, "New Location");
            UserEntity owner = createMockUser(1L, TEST_USERNAME);
            Event newEvent = createMockEvent(2L, "New Event", "New Description", location, null, owner, null);

            when(eventMapper.newEventDTOtoEvent(newEventDTO)).thenReturn(newEvent);
            when(eventRepository.save(any(Event.class))).thenReturn(newEvent);

            long eventId = eventService.addEvent(newEventDTO);

            assertEquals(2L, eventId);
        }

        @Test
        void modifyEventTest() {
            EventDTO eventDTO = new EventDTO(
                    TEST_EVENT_ID, LocalDate.now(), "Updated Event", "Updated Description",
                    new LocationInEventDTO(1L, "Updated Location", 40.7128, -74.0060),
                    List.of(new UserInEventDTO(1L, TEST_USERNAME)),
                    new UserInEventDTO(1L, TEST_USERNAME), EventSize.SMALL,
                    Set.of(new TagDTO(1L, "Updated Tag", 1, "black")), "Active"
            );

            Event updatedEvent = createMockEvent(TEST_EVENT_ID, "Updated Event", "Updated Description", null, null, null, null);

            when(eventMapper.eventDTOToEvent(eventDTO)).thenReturn(updatedEvent);
            when(eventRepository.save(any(Event.class))).thenReturn(updatedEvent);

            long result = eventService.modifyEvent(eventDTO);
            long expected=1L;
            assertEquals(expected, result);

        }
    }

    @Nested
    class DeleteEventTests {

        @Test
        void deleteEventByIdTest() {
            when(eventRepository.deleteEventById(TEST_EVENT_ID)).thenReturn(true);

            boolean result = eventService.deleteEventById(TEST_EVENT_ID);

            assertTrue(result, "The deleteEventById method should return true for successful deletion");

            verify(eventRepository, times(1)).deleteEventById(TEST_EVENT_ID);
        }
    }


    // Helper Methods
    private Event createMockEvent(long id, String name, String description, Location location, Set<UserEntity> users, UserEntity owner, Set<Tag> tags) {
        return new Event(id, LocalDate.now(), name, description, location, users, owner, EventSize.SMALL, tags, "ACTIVE");
    }

    private UserEntity createMockUser(long id, String username) {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setUsername(username);
        return user;
    }

    private Location createMockLocation(long id, String name) {
        Location location = new Location();
        location.setId(id);
        location.setName(name);
        location.setLatitude(40.7128);
        location.setLongitude(-74.0060);
        return location;
    }

    private Tag createMockTag(long id, String name) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        return tag;
    }
}