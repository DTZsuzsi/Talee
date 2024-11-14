package com.codecool.DTO.user;

import com.codecool.DTO.event.EventDTO;
import com.codecool.model.users.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public record UserDTO(long id, String username, @JsonIgnore Set<EventDTO> events, Role role) {
}
