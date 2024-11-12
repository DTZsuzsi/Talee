package com.codecool.model.tags;

import jakarta.persistence.*;
import lombok.Data;

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
}
