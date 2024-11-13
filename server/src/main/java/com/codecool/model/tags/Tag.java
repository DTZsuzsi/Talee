package com.codecool.model.tags;

import com.codecool.model.events.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @ManyToMany(mappedBy = "tags")
private Set<Event> event;
    @ManyToOne
    @JoinColumn(name="tag_category_id")
    private TagCategory category;

    private Timestamp createdAt=Timestamp.valueOf(LocalDateTime.now());

    public Tag() {
    }

    public Tag(String name, TagCategory category) {
        this.name = name;
        this.category = category;
    }
}
