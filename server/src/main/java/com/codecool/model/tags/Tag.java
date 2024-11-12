package com.codecool.model.tags;

import com.codecool.model.events.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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


}
