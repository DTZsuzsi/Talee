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
  private long id;
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


}
