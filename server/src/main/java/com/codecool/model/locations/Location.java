package com.codecool.model.locations;

import com.codecool.model.events.Event;
import com.codecool.model.tags.Tag;
import com.codecool.model.users.UserEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String address;
  private double latitude;
  private double longitude;
  private String phone;
  private String email;
  private String website;
  private String facebook;
  private String instagram;

  @ManyToOne
  private UserEntity adminUser;


  @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<OpeningHours> openingHours;


  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Event> events = new ArrayList<>();

  @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "location_tag",
          joinColumns = @JoinColumn(name = "location_id"),
          inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags;

  private String description;

  public Location(long id, String name){
    this.id = id;
    this.name = name;
  }
}
