package com.codecool.talee.model.tags;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class TagCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;
    @OneToMany (mappedBy = "tagCategory")
    private Set<Tag> tags;


    public TagCategory(String name, String color) {
        this.name = name;
        this.color = color;
    }

}
