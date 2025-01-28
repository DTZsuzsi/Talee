package com.codecool.talee.model.users;

import com.codecool.talee.model.events.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Table(name = "talee_user")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<Event> events;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
