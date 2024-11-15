package com.codecool.model.users;

import com.codecool.model.events.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Table(name = "talee_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;

    @ManyToMany(mappedBy = "users")
    private Set<Event> events;

//    @ManyToMany(mappedBy = "creatorUser")
//    private Set<Location> locations;

    @ManyToOne
    private Role role;
}
