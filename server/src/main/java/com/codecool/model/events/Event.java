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
import java.util.List;
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
    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_tag",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
    private String status;


    public Event(long id, LocalDate date, String name, String description, Location location, Set<UserEntity> users, String owner, String size, List<Tag> tags, String status) {

        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.location = location;
        this.users = users;
        this.owner = owner;
        this.size = size;
        this.tags = tags;
        this.status = status;
    }

    public Event(LocalDate date, String name, String description, Location location, String owner, String size, List<Tag> tags, String status) {
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

