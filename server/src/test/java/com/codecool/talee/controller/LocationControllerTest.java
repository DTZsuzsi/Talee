package com.codecool.talee.controller;

import com.codecool.talee.DTO.location.LocationDTO;
import com.codecool.talee.DTO.location.NewLocationDTO;
import com.codecool.talee.service.LocationService;
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

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    String newLocation = """
            {
                "name": "Test Location",
                "address": "123 Main St",
                "latitude": 40.7128,
                "longitude": -74.0060,
                "phone": "123-456-7890",
                "email": "test@location.com",
                "description": "A test location"
            }
            """;

    @BeforeAll
    void setUpOnce() {
        when(locationService.getAllLocations()).thenReturn(Collections.emptyList());
    }

    @Test
    void getAllLocations_returns200AndEmptyList() throws Exception {
        mockMvc.perform(get("/api/locations/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getLocationById_returns200() throws Exception {
        LocationDTO mockLocation = new LocationDTO(1, "Test Location", "123 Main St", "123-456-7890", "test@location.com", "Description", null, Collections.emptyList(), Collections.emptySet(), 40.7128, -74.0060, Collections.emptySet());
        when(locationService.getLocationById(anyLong())).thenReturn(mockLocation);

        mockMvc.perform(get("/api/locations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Location"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void addLocation_returns201() throws Exception {
        when(locationService.addLocation(any(NewLocationDTO.class), any())).thenReturn(1L);

        mockMvc.perform(post("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newLocation)
                        .header("Authorization", "Bearer validToken"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "owner", roles = {"LOCATION_OWNER"})
    void deleteLocation_returns200() throws Exception {
        when(locationService.deleteLocation(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/locations/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "owner", roles = {"LOCATION_OWNER"})
    void updateLocation_returns200() throws Exception {
        when(locationService.updateLocation(any(LocationDTO.class))).thenReturn(true);

        mockMvc.perform(patch("/api/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newLocation))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user")
    void getLocationsByTagName_returns200() throws Exception {
        when(locationService.getLocationsByTag("TestTag")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/locations/tagsfilter/TestTag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
