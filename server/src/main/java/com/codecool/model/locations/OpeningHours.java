package com.codecool.model.locations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OpeningHours {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Enumerated(EnumType.STRING)
  private DayOfWeek dayOfWeek;
  private LocalTime openingTime;
  private LocalTime closingTime;
  @ManyToOne
  private Location location;

}
