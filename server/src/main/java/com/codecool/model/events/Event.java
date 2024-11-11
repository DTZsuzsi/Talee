package com.codecool.model.events;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Event {
    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private int location_id;
    private Set<String> users;
    private String owner;
    private EventSize size;
    private Set<String> tags;
    private EventStatus status;
    private Timestamp timestamp;


    public Event(int id, LocalDate date, String name, String description, int location_id, Set<String> users, String owner, EventSize size, Set<String> tags, EventStatus status, Timestamp timestamp) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.location_id = location_id;

        if (users != null) {
            this.users = new HashSet<>();
        } else {
            this.users = users;
        }
        this.owner = owner;
        this.size = size;
        this.tags = tags;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Event(LocalDate date, String name, String description, int location_id,  String owner, EventSize size, Set<String> tags, EventStatus status) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.location_id = location_id;
        this.owner = owner;
        this.size = size;
        this.tags = tags;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLocation_id() {
        return location_id;
    }

    public Set<String> getUsers() {
        return users;
    }

    public String getOwner() {
        return owner;
    }

    public EventSize getSize() {
        return size;
    }

    public Set<String> getTags() {
        return tags;
    }

    public EventStatus getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public LocalDate getDate() {
        return date;
    }
}
