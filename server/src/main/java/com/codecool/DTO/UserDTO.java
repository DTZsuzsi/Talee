package com.codecool.DTO;

import com.codecool.model.events.Event;
import com.codecool.model.users.Role;

import java.util.Set;

public record UserDTO(long id, String username, Set<Event> events, Role role) {
}
