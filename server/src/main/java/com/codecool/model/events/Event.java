package com.codecool.model.events;

import com.codecool.model.Tag;
import com.codecool.model.User;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private String name;
    private String description;
    private int location_id;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_user", // Join table name
            joinColumns = @JoinColumn(name = "event_id"), // Foreign key in join table for Event
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key in join table for User
    )    private Set<User> users;
    private String owner;
    private String size;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_tag", // join table name
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
    private String status;
    private Timestamp timestamp;


    public Event() {
    }

    public Event(int id, LocalDate date, String name, String description, int location_id, Set<User> users, String owner, String size, Set<Tag> tags, String status, Timestamp timestamp) {
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

    public Event(LocalDate date, String name, String description, int location_id, String owner, String size, Set<Tag> tags, String status) {
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

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

