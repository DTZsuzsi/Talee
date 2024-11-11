package com.codecool.DTO;

import com.codecool.model.events.EventSize;
import com.codecool.model.events.EventStatus;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

public record EventDTO(int id, LocalDate date, String name, String description, int location_id, Set<String> users, String owner, EventSize size, Set<String> tags, EventStatus status, Timestamp timestamp) {
}
