package com.codecool.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {
  @Autowired
  private MockMvc mockMvc;

  private String userJson;

//  @BeforeEach
//  void setUp() {
//
//  }

  @Test
  void whenUsernameNotInDBAndPasswordSent_thenRegisterSuccessfully() throws Exception {
    userJson = """
            {
            "username": "testUser",
            "password": "password"
            }
            """;

    mockMvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(status().isCreated());
  }

  @Test
  void whenUsernameInDBAndPasswordSent_thenBadRequestStatusReceived() throws Exception {
    userJson = """
            {
            "username": "matet",
            "password": "password"
            }
            """;

    mockMvc.perform(post("/api/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userJson))
            .andExpect(status().isBadRequest());
  }

  @Test
  void whenLoginWithValidCredentials_thenLoginSuccessfully() throws Exception {
    userJson = """
            {
            "username": "matet",
            "password": "admin"
            }
            """;

    mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
            .andExpect(status().isOk());
  }
}