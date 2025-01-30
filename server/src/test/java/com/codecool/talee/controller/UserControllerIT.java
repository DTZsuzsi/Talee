package com.codecool.talee.controller;

import com.codecool.talee.DTO.user.NewUserDTO;
import com.codecool.talee.DTO.user.UserDTO;
import com.codecool.talee.service.UserService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithMockUser(username = "user", roles = {"USER"})
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    String newUser = """
            {
                "username": "newuser",
                "password": "password123",
                "email": "newuser@example.com"
            }
            """;

    String updatedUser = """
            {
                "id": 1,
                "username": "updateduser",
                "events": [],
                "roles": []
            }
            """;

    @Test
    void getAllUsers_returns200AndEmptyList() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getUserById_returns200() throws Exception {
        UserDTO userDTO = new UserDTO(1, "testuser", Collections.emptySet(), Collections.emptySet());
        when(userService.getUserById(1)).thenReturn(userDTO);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void addUser_returns201() throws Exception {
        UserDTO userDTO = new UserDTO(1, "newuser", Collections.emptySet(), Collections.emptySet());
        when(userService.addUser(any(NewUserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUser))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("newuser"));
    }

    @Test
    void updateUser_returns200() throws Exception {
        UserDTO userDTO = new UserDTO(1, "updateduser", Collections.emptySet(), Collections.emptySet());
        when(userService.modifyUser(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(patch("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUser))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updateduser"));
    }

    @Test
    void deleteUser_returns200() throws Exception {
        when(userService.deleteUser(1)).thenReturn(true);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
