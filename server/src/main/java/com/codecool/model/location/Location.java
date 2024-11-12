package com.codecool.model.location;

import com.codecool.model.events.Event;
import com.codecool.model.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
//  private ContactInfo contactInfo;
  private String address;
  private String phone;
  private String email;
  private String website;
  private String facebook;
  private String instagram;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User adminUser;

//  private OpeningHours openingHours;
  @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Event> events;
  private String description;

//  public Location(int id, String name, String address, String phone, String email, String website, String facebook, String instagram, OpeningHours openingHours, List<Event> events, String description) {
//    this.id = id;
//    this.name = name;
//    this.address = address;
//    this.phone = phone;
//    this.email = email;
//    this.website = website;
//    this.facebook = facebook;
//    this.instagram = instagram;
////    this.openingHours = openingHours;
//    this.events = events;
//    this.description = description;
//  }
//
//  public Location(String name, String address, String phone, String email, String website, String facebook, String instagram, OpeningHours openingHours, String description) {
//    this.name = name;
//    this.address = address;
//    this.phone = phone;
//    this.email = email;
//    this.website = website;
//    this.facebook = facebook;
//    this.instagram = instagram;
////    this.openingHours = openingHours;
//    this.events = new ArrayList<>();
//    this.description = description;
//  }

  //unused
  //  public Location(int id, String name, String address, String phone, String email, String description,
//                  OpeningHours openingHours) {
//    this.id = id;
//    this.name = name;
////    this.contactInfo = contactInfo;
//    this.address = address;
//    this.phone = phone;
//    this.email = email;
//
//    this.description = description;
//    this.openingHours = openingHours;
//    events = new ArrayList<>();
//  }


//  public Location(String name, String address, String phone, String email, String website, String facebook,
//                  String instagram, OpeningHours openingHours, List<Event> events, String description) {
//    this.name = name;
//    this.address = address;
//    this.phone = phone;
//    this.email = email;
//    this.website = website;
//    this.facebook = facebook;
//    this.instagram = instagram;
//    this.openingHours = openingHours;
//    this.events = events;
//    this.description = description;
//  }

//  public Location(int id, String name, String address, String phone, String email, String description) {
//    this.id = id;
//    this.name = name;
//    this.address = address;
//    this.phone = phone;
//    this.email = email;
//    this.description = description;
//    events = new ArrayList<>();
//  }

  public boolean addEvent(Event event) {
    return events.add(event);
  }

//  public boolean changeOpeningHoursForDay(DayOfWeek day, TimeRange newHours) {
//    openingHours.setOpeningHoursForDay(day, newHours);
//    return true;
//  }
//
//  public boolean deleteOpeningHoursForDay(DayOfWeek day) {
//    openingHours.deleteOpeningHoursForDay(day);
//    return true;
//  }

  public void addWebsite(String website) {
    this.website = website;
  }

  public void addFacebook(String facebook) {
    this.facebook = facebook;
  }

  public void addInstagram(String instagram) {
    this.instagram = instagram;
  }


}
