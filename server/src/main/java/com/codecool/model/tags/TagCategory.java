package com.codecool.model.tags;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class TagCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;
    @OneToMany (mappedBy = "category")
    private Set<Tag> tags;

    private Timestamp createdAt=Timestamp.valueOf(LocalDateTime.now());

    public TagCategory(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public TagCategory() {
    }
}
