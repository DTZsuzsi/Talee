package com.codecool.DTO;

import com.codecool.model.Tag;
import com.codecool.model.users.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

public record EventDTO(int id, LocalDate date, String name, String description, int location_id, Set<User> users, String owner, String size, Set<Tag> tags, String status, Timestamp timestamp) {
}
