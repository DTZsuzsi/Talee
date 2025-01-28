package com.codecool.talee.service;

import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.DTO.event.NewEventDTO;
import com.codecool.talee.DTO.location.LocationInEventDTO;
import com.codecool.talee.DTO.tag.TagDTO;
import com.codecool.talee.DTO.user.UserInEventDTO;
import com.codecool.talee.model.events.Event;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @InjectMocks
    private EventService eventService;

    private static final long TEST_EVENT_ID = 1L;
    private static final String TEST_USERNAME = "testuser";

    private Event mockEvent;
    private NewEventDTO newEventDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Location location = createMockLocation(1L, "Test Location");
        UserEntity owner = createMockUser(1L, TEST_USERNAME);
        Tag tag = createMockTag(1L, "Test Tag");

        mockEvent = createMockEvent(TEST_EVENT_ID, "Test Event", "Test Description", location, Set.of(owner), owner, Set.of(tag));

        newEventDTO = new NewEventDTO(
                LocalDate.now(),
                "Test Event",
                "Test Description",
                new LocationInEventDTO(1L, "Test Location", 40.7128, -74.0060),
                new HashSet<>(),
                new UserInEventDTO(1L, TEST_USERNAME),
                "SMALL",
                new HashSet<>(),
                "ACTIVE"
        );
    }

    @Nested
    class GetEventTests {

        @Test
        void getEventByIdTest() {
            when(eventRepository.findEventById(TEST_EVENT_ID)).thenReturn(Optional.of(mockEvent));

            EventDTO eventDTO = eventService.getEventById(TEST_EVENT_ID);

            assertNotNull(eventDTO);
            assertEquals(TEST_EVENT_ID, eventDTO.id());
            assertEquals("Test Event", eventDTO.name());
            assertEquals("Test Description", eventDTO.description());
            assertEquals(1, eventDTO.tags().size());
            assertEquals(1, eventDTO.users().size());
            assertEquals("Test Location", eventDTO.location().name());
        }

        @Test
        void getAllEventsTest() {
            Event event1 = createMockEvent(1L, "Event 1", "Description", null, null, null, null);
            Event event2 = createMockEvent(2L, "Event 2", "Description", null, null, null, null);

            when(eventRepository.findAll()).thenReturn(List.of(event1, event2));

            List<EventDTO> events = eventService.getAllEvents();

            assertEquals(2, events.size());
        }

        @Test
        void getEventsByTagTest() {
            Tag tag = createMockTag(1L, "Test Tag");
            Event event = createMockEvent(1L, "Test Event", "Description", null, null, null, Set.of(tag));

            when(tagRepository.findByName("Test Tag")).thenReturn(Optional.of(tag));
            when(eventRepository.findEventsByTagsContaining(tag)).thenReturn(List.of(event));

            List<EventDTO> events = eventService.getEventsByTag("Test Tag");

            assertEquals(1, events.size());
            assertEquals("Test Event", events.get(0).name());
        }

        @Test
        void findAllEventsByLocationIdTest() {
            long locationId = 1L;

            Event event = createMockEvent(1L, "Test Event", "Description", null, null, null, null);

            when(eventRepository.findAllByLocationId(locationId)).thenReturn(List.of(event));

            List<EventDTO> events = eventService.findAllEventsByLocationId(locationId);

            assertEquals(1, events.size());
            assertEquals("Test Event", events.get(0).name());
        }
    }

    @Nested
    class ModifyEventTests {

        @Test
        void addEventTest() {
            Location location = createMockLocation(1L, "New Location");
            UserEntity owner = createMockUser(1L, TEST_USERNAME);

            when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
            when(userRepository.findById(1L)).thenReturn(Optional.of(owner));
            when(eventRepository.save(any(Event.class))).thenReturn(mockEvent);

            long eventId = eventService.addEvent(newEventDTO);

            assertEquals(TEST_EVENT_ID, eventId);
        }

        @Test
        void modifyEventTest() {
            EventDTO eventDTO = new EventDTO(
                    TEST_EVENT_ID, LocalDate.now(), "Updated Event", "Updated Description",
                    new LocationInEventDTO(1L, "Updated Location", 40.7128, -74.0060),
                    List.of(new UserInEventDTO(1L, TEST_USERNAME)),
                    new UserInEventDTO(1L, TEST_USERNAME), "SMALL",
                    Set.of(new TagDTO(1L, "Updated Tag", 1, "#FFFFFF")), "Active");

            when(eventRepository.save(any(Event.class))).thenReturn(mockEvent);

            boolean result = eventService.modifyEvent(eventDTO);

            assertTrue(result);
        }
    }

    @Nested
    class UserEventInteractionTests {

        @Test
        void applyUserToEventTest() {
            String token = "validToken";

            UserEntity user = createMockUser(1L, TEST_USERNAME);
            Event event = createMockEvent(TEST_EVENT_ID, "Test Event", "Description", null, new HashSet<>(), null, null);

            when(jwtUtils.getUsernameFromJwtToken(token)).thenReturn(TEST_USERNAME);
            when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(user));
            when(eventRepository.findEventById(TEST_EVENT_ID)).thenReturn(Optional.of(event));
            when(eventRepository.save(any(Event.class))).thenReturn(event);

            boolean result = eventService.applyUserToEvent(TEST_EVENT_ID, token);

            assertTrue(result);
            assertTrue(event.getUsers().contains(user));
        }

        @Test
        void deleteUserFromEventTest() {
            long userId = 1L;

            UserEntity user = createMockUser(userId, TEST_USERNAME);
            Event event = createMockEvent(TEST_EVENT_ID, "Test Event", "Description", null, Set.of(user), null, null);

            when(eventRepository.findEventById(TEST_EVENT_ID)).thenReturn(Optional.of(event));
            when(eventRepository.save(any(Event.class))).thenReturn(event);

            boolean result = eventService.deleteUserFromEvent(TEST_EVENT_ID, userId);

            assertTrue(result);
            assertFalse(event.getUsers().contains(user));
        }
    }

    @Nested
    class DeleteEventTests {

        @Test
        void deleteEventByIdTest() {
            doNothing().when(eventRepository).deleteEventById(TEST_EVENT_ID);

            eventService.deleteEventById(TEST_EVENT_ID);

            verify(eventRepository, times(1)).deleteEventById(TEST_EVENT_ID);
        }
    }

    // Helper Methods
    private Event createMockEvent(long id, String name, String description, Location location, Set<UserEntity> users, UserEntity owner, Set<Tag> tags) {
        return new Event(id, LocalDate.now(), name, description, location, users, owner, "SMALL", tags, "ACTIVE");
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
