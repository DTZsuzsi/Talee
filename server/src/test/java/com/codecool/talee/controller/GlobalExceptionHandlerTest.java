package com.codecool.talee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@WithMockUser(username = "user", roles = {"USER"})
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void handleUserNotFoundException_returns404() throws Exception {
        mockMvc.perform(get("/api/users/999")) // Assume this endpoint triggers UserNotFoundException
                .andExpect(status().isNotFound())
                .andExpect(content().string("User with id 999 not found")); // Adjust to your exception's message
    }

    @Test
    void handleLocationNotFoundException_returns404() throws Exception {
        mockMvc.perform(get("/api/locations/999")) // Assume this endpoint triggers LocationNotFoundException
                .andExpect(status().isNotFound())
                .andExpect(content().string("Location with id 999 not found")); // Adjust to your exception's message
    }
}