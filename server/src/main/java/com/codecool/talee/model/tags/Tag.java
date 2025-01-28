package com.codecool.talee.model.tags;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="tag_category_id")
    private TagCategory tagCategory;

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
