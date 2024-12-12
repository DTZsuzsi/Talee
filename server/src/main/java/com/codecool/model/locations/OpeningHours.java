package com.codecool.model.locations;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumMap;
import java.util.Map;

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
