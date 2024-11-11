package com.codecool.model;

import com.codecool.model.events.Event;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Tag {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String name;
    @ManyToMany(mappedBy = "tags")
private Set<Event> event;

public Tag() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvent() {
        return event;
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }
}
