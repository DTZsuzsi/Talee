package com.codecool.service;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.mapper.LocationMapper;
import com.codecool.mapper.TagMapper;
import com.codecool.mapper.UserMapper;
import com.codecool.model.locations.Location;
import com.codecool.repository.LocationRepository;
import com.codecool.repository.RoleRepository;
import com.codecool.repository.TagCategoryRepository;
import com.codecool.repository.UserRepository;
import com.codecool.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
  @Mock
  private LocationRepository locationRepository;
  @Mock
  private OpeningHoursService openingHoursService;
  @Mock
  private TagCategoryRepository tagCategoryRepository;
  @Mock
  private UserService userService;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private LocationMapper locationMapper;
  @Mock
  private UserMapper userMapper;
  @Mock
  private TagMapper tagMapper;
  @Mock
  private JWTUtils jwtUtils;
  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private LocationService locationService;

  private List<Location> mockLocations;

  @BeforeEach
  void setUp() {
    mockLocations = new ArrayList<>();

    Mockito.when(locationRepository.findAll()).thenReturn(mockLocations);
  }

  @Test
  void whenLocationsGetAllLocations_returnsListOfLocationDTOs() {
    Location mockLocation1 = Mockito.mock(Location.class);
    mockLocation1.setId(1L);
    mockLocation1.setName("Location1");
    mockLocations.add(mockLocation1);

    Location mockLocation2 = Mockito.mock(Location.class);
    mockLocation2.setId(2L);
    mockLocation2.setName("Location2");
    mockLocations.add(mockLocation2);

    List<LocationDTO> locations = locationService.getAllLocations();

    assertEquals(mockLocations.size(), locations.size());
    assertEquals(mockLocations.get(0).getName(), locations.get(0).name());
    assertEquals(mockLocations.get(1).getName(), locations.get(1).name());

  }

  @Test
  void whenNoLocationsGetAllLocations_returnsEmptyList() {
    List<LocationDTO> locations = locationService.getAllLocations();
    assertTrue(locations.isEmpty());

  }

  @Test
  void getLocationById() {
  }

  @Test
  void addLocation() {
  }

  @Test
  void deleteLocation() {
  }

  @Test
  void addTagToLocation() {
  }

  @Test
  void deleteTagFromLocation() {
  }

  @Test
  void updateLocation() {
  }
}