package com.codecool.model.locations;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.Map;

public class OpeningHours {
  private final Map<DayOfWeek, TimeRange> openingHoursMap;

  public OpeningHours() {
    openingHoursMap = new EnumMap<>(DayOfWeek.class);
  }

  //TODO change access modifier to package-private?
  public void setOpeningHoursForDay(DayOfWeek day, TimeRange businessHours) {
    openingHoursMap.put(day, businessHours);
  }

  //TODO change access modifier to package-private?
  public void deleteOpeningHoursForDay(DayOfWeek day) {
    openingHoursMap.remove(day);
  }
}
