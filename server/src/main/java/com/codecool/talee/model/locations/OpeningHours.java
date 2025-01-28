package com.codecool.talee.model.locations;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class OpeningHours {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;
  private LocalTime openingTime;
  private LocalTime closingTime;
  @ManyToOne(cascade = CascadeType.REMOVE)
  private Location location;

}
