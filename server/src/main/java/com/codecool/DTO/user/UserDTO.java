package com.codecool.DTO.user;

import com.codecool.DTO.event.EventDTO;
import com.codecool.model.events.Event;
import com.codecool.model.users.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

public record UserDTO(long id, String username, @JsonManagedReference Set<Event> events, Role role) {
}
