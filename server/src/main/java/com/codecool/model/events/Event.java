package com.codecool.model.events;

import com.codecool.model.tags.Tag;
import com.codecool.model.locations.Location;
import com.codecool.model.users.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private String name;
    private String description;

    @ManyToOne
    private Location location;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "event_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users;
    private String owner;
    private String size;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "event_tag", // join table name
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
    private String status;
    private Timestamp timestamp;



    public Event(long id, LocalDate date, String name, String description, Location location, Set<UserEntity> users, String owner, String size, Set<Tag> tags, String status, Timestamp timestamp) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.location = location;

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

    public Event(LocalDate date, String name, String description, Location location, String owner, String size, Set<Tag> tags, String status) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.location = location;
        this.owner = owner;
        this.size = size;
        this.tags = tags;
        this.status = status;
    }


    public void addTag(Tag tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

}

