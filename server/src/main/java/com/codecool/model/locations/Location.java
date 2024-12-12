package com.codecool.model.locations;

import com.codecool.model.events.Event;
import com.codecool.model.tags.Tag;
import com.codecool.model.users.UserEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
//  private ContactInfo contactInfo;
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


  @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
  private List<OpeningHours> openingHours;

  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Event> events;


  private String description;

  @ManyToMany( fetch = FetchType.EAGER)
  @JoinTable(
          name = "location_tag",
          joinColumns = @JoinColumn(name = "location_id"),
          inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags;


  public boolean addEvent(Event event) {
    return events.add(event);
  }

  public boolean addOpeningHours(OpeningHours openingHours) {
    return this.openingHours.add(openingHours);
  }

  public void addTag(Tag tag) {
    tags.add(tag);
  }

  public Location() {
  }

  public Location(long id, String name, String address, String phone, String email, String website, String facebook, String instagram, UserEntity adminUser, List<OpeningHours> openingHours, List<Event> events, String description, Set<Tag> tags, double latitude, double longitude) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.website = website;
    this.facebook = facebook;
    this.instagram = instagram;
    this.adminUser = adminUser;
    this.openingHours = openingHours;
    this.events = events;
    this.description = description;
    this.tags = tags;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Location(long id, String name){
    this.id = id;
    this.name = name;
  }
}
