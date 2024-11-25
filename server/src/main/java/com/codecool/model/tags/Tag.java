package com.codecool.model.tags;

import com.codecool.model.events.Event;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Tag {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String name;
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.PERSIST)
private Set<Event> event;
    @ManyToOne
    @JoinColumn(name="tag_category_id")
    private TagCategory tagCategory;


    public Tag() {
    }

    public Tag(String name, TagCategory category) {
        this.name = name;
        this.tagCategory = category;
    }

    public Tag(long id, String name,  TagCategory tagCategory) {
        this.id = id;
        this.name = name;
        this.tagCategory = tagCategory;
    }

    public Tag(long id) {
        this.id = id;
    }
}
