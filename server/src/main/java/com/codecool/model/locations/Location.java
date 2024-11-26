package com.codecool.model.locations;

import com.codecool.model.events.Event;
import com.codecool.model.tags.Tag;
import com.codecool.model.users.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Data
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
  private User adminUser;

  @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE)
  private List<OpeningHours> openingHours;

  @OneToMany(mappedBy = "location", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<Event> events;


  private String description;

  @ManyToMany( fetch = FetchType.EAGER)
  @JoinTable(
          name = "location_tag",
          joinColumns = @JoinColumn(name = "location_id"),
          inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private List<Tag> locationTags;


  public boolean addEvent(Event event) {
    return events.add(event);
  }

  public boolean addOpeningHours(OpeningHours openingHours) {
    return this.openingHours.add(openingHours);
  }

  public void addTag(Tag tag) {
    locationTags.add(tag);
  }

}
