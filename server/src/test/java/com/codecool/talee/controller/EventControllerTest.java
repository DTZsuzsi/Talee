package com.codecool.talee.controller;

import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.DTO.event.NewEventDTO;
import com.codecool.talee.repository.EventRepository;
import com.codecool.talee.service.EventService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    String newEvent = """
            {
                "id": 0,
                "date": "2025-01-01",
                "name": "Sample Event",
                "description": "This is a test event.",
                "locationInEventDTO": {
                    "locationId": 1,
                    "name": "Sample Location",
                    "address": "123 Test St"
                },
                "users": [],
                "owner": {
                    "id": 1,
                    "username": "ownerUser",
                    "email": "owner@test.com"
                },
                "size": "SMALL",
                "tags": [
                    { "id": 1, "name": "Test" },
                    { "id": 2, "name": "Sample" }
                ],
                "status": "ACTIVE"
            }
            """;

    @BeforeAll
    void setUpOnce() {
        // Mock any required data if necessary
        when(eventRepository.findAll()).thenReturn(Collections.emptyList());
    }

    @Test
    void getAllEvents_returns200AndEmptyList() throws Exception {
        mockMvc.perform(get("/api/events/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0))); // Assuming no events exist initially
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void createEvent_returns201() throws Exception {
        when(eventService.addEvent(any(NewEventDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newEvent)
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isCreated());
    }

    @Test
    void getEventById_returns200() throws Exception {
        EventDTO mockEvent = new EventDTO(1, LocalDate.now(), "Sample Event", "This is a test event.", null, List.of(), null, "SMALL", Set.of(), "ACTIVE");
        when(eventService.getEventById(anyLong())).thenReturn(mockEvent);

        mockMvc.perform(get("/api/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sample Event"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER", "EVENT_OWNER"})
    void modifyEvent_returns200() throws Exception {
        when(eventService.modifyEvent(any(EventDTO.class))).thenReturn(true);

        mockMvc.perform(patch("/api/events/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newEvent)
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "user", roles = {"USER", "EVENT_OWNER"})
    void deleteEvent_returns200() throws Exception {
        when(eventService.deleteEventById(1L)).thenReturn(true);;

        mockMvc.perform(delete("/api/events/1")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void userApplyToEvent_returns200() throws Exception {
        when(eventService.applyUserToEvent(1L, "validToken")).thenReturn(true);

        mockMvc.perform(post("/api/events/apply/1")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER", "EVENT_OWNER"})
    void deleteUser_returns200() throws Exception {
        when(eventService.deleteUserFromEvent(1L, 1)).thenReturn(true);

        mockMvc.perform(delete("/api/events/user/1")
                        .param("userId", "1")
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void getEventsByLocation_returns200() throws Exception {
        when(eventService.findAllEventsByLocationId(1L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/events/locations/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void getEventsByTag_returns200() throws Exception {
        when(eventService.getEventsByTag("TestTag")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/events/tagsfilter/TestTag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}