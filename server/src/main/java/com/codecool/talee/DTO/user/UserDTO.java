package com.codecool.talee.DTO.user;

import com.codecool.talee.DTO.event.EventDTO;
import com.codecool.talee.model.users.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public record UserDTO(long id, String username, @JsonIgnore Set<EventDTO> events, Set<Role> roles) {
}
