package com.codecool.talee.service;

import com.codecool.talee.DTO.location.LocationWithoutOpeningHoursDTO;
import com.codecool.talee.DTO.location.NewOpeningHoursDTO;
import com.codecool.talee.exception.LocationNotFoundException;
import com.codecool.talee.model.locations.Location;
import com.codecool.talee.model.locations.OpeningHours;
import com.codecool.talee.repository.LocationRepository;
import com.codecool.talee.repository.OpeningHoursRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OpeningHoursServiceTest {
  @Mock
  private OpeningHoursRepository openingHoursRepository;

  @Mock
  private LocationRepository locationRepository;

  @InjectMocks
  private OpeningHoursService openingHoursService;

  @Test
  public void whenAddNewOpeningHoursForExistingLocation_thenNewOpeningHoursAdded() {
    LocationWithoutOpeningHoursDTO mockLocationDTO = Mockito.mock(LocationWithoutOpeningHoursDTO.class);
    Mockito.when(mockLocationDTO.id()).thenReturn(1L);

    NewOpeningHoursDTO mockOpeningHours = Mockito.mock(NewOpeningHoursDTO.class);
    Mockito.when(mockOpeningHours.location()).thenReturn(mockLocationDTO);
    Mockito.when(mockOpeningHours.day()).thenReturn(DayOfWeek.SUNDAY);
    Mockito.when(mockOpeningHours.openingTime()).thenReturn(LocalTime.of(14, 0));
    Mockito.when(mockOpeningHours.closingTime()).thenReturn(LocalTime.of(17, 0));

    Location existingLocation = new Location();
    existingLocation.setId(1L);
    Mockito.when(locationRepository.findById(1L)).thenReturn(Optional.of(existingLocation));

    OpeningHours savedOpeningHours = new OpeningHours();
    savedOpeningHours.setId(100L);
    Mockito.when(openingHoursRepository.save(Mockito.any(OpeningHours.class))).thenReturn(savedOpeningHours);

    long savedId = openingHoursService.addNewOpeningHours(mockOpeningHours);
    assertEquals(savedOpeningHours.getId(), savedId);
  }

  @Test
  public void whenAddNewOpeningHoursForNonExistingLocation_thenExceptionIsThrown() {
    LocationWithoutOpeningHoursDTO mockLocationDTO = Mockito.mock(LocationWithoutOpeningHoursDTO.class);
    Mockito.when(mockLocationDTO.id()).thenReturn(999L);

    NewOpeningHoursDTO mockOpeningHours = Mockito.mock(NewOpeningHoursDTO.class);
    Mockito.when(mockOpeningHours.location()).thenReturn(mockLocationDTO);

    Mockito.when(locationRepository.findById(999L)).thenReturn(Optional.empty());

    assertThrows(LocationNotFoundException.class, () -> openingHoursService.addNewOpeningHours(mockOpeningHours));

  }

  @Test
  public void whenDeleteOpeningHoursSuccessful_thenReturnTrue() {
    long locationId = 1L;
    Mockito.when(openingHoursRepository.deleteByLocationId(locationId)).thenReturn(locationId);

    boolean result = openingHoursService.deleteOpeningHoursByLocationId(locationId);

    assertTrue(result, "");
  }

  @Test
  public void whenDeleteOpeningHoursFails_thenReturnFalse() {
    long locationId = 2L;
    long mockReturn = -1;
    Mockito.when(openingHoursRepository.deleteByLocationId(locationId)).thenReturn(mockReturn);
    boolean result = openingHoursService.deleteOpeningHoursByLocationId(locationId);
    assertFalse(result, "");
  }
}