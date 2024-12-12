package com.codecool.service;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.location.NewLocationDTO;
import com.codecool.exception.LocationNotFoundException;
import com.codecool.mapper.LocationMapper;
import com.codecool.mapper.TagMapper;
import com.codecool.mapper.UserMapper;
import com.codecool.model.locations.Location;
import com.codecool.model.users.Role;
import com.codecool.model.users.UserEntity;
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
import java.util.Optional;

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

    Mockito.when(locationRepository.findAll()).thenReturn(mockLocations);

    List<LocationDTO> locations = locationService.getAllLocations();

    assertEquals(mockLocations.size(), locations.size());
    assertEquals(mockLocations.get(0).getName(), locations.get(0).name());
    assertEquals(mockLocations.get(1).getName(), locations.get(1).name());

  }

  @Test
  void whenNoLocationsGetAllLocations_returnsEmptyList() {
    Mockito.when(locationRepository.findAll()).thenReturn(mockLocations);
    List<LocationDTO> locations = locationService.getAllLocations();
    assertTrue(locations.isEmpty());

  }

  @Test
  void whenExistingLocationGetLocationById_returnsLocationDTO() {
    Location mockLocation = Mockito.mock(Location.class);
    mockLocation.setId(1L);
    mockLocation.setName("Location1");
    mockLocations.add(mockLocation);

    assertEquals(mockLocations.get(0).getId(), mockLocation.getId());
    assertEquals(mockLocations.get(0).getName(), mockLocation.getName());

  }

  @Test
  void whenNotExistingLocationGetLocationById_throwException() {
    Location mockNotExistingLocation = Mockito.mock(Location.class);
    mockNotExistingLocation.setId(33L);

    Mockito.when(locationRepository.findById(33L)).thenReturn(Optional.empty());
    assertThrows(LocationNotFoundException.class, () -> locationService.getLocationById(33L));

  }

  @Test
  void whenLoggedInUserAddLocation_returnsSavedLocationsId() {
    NewLocationDTO newLocationDTO = Mockito.mock(NewLocationDTO.class);
    Mockito.when(newLocationDTO.name()).thenReturn("New Location");
    String mockToken = "valid.token";
    String mockUserName = "valid.username";
    Mockito.when(jwtUtils.getUsernameFromJwtToken(mockToken)).thenReturn(mockUserName);

    UserEntity mockUser = Mockito.mock(UserEntity.class);
    Mockito.when(userRepository.findByUsername(mockUserName)).thenReturn(Optional.of(mockUser));

    Role locationOwnerRole = Mockito.mock(Role.class);
    locationOwnerRole.setId(1L);
    locationOwnerRole.setName("ROLE_LOCATION_OWNER");
    Mockito.when(roleRepository.findByName("ROLE_LOCATION_OWNER")).thenReturn(Optional.of(locationOwnerRole));

    Location savedMockLocation = Mockito.mock(Location.class);
    Mockito.when(savedMockLocation.getId()).thenReturn(3L);
    Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(savedMockLocation);

    long savedId = locationService.addLocation(newLocationDTO, mockToken);
    assertEquals(savedMockLocation.getId(), savedId);


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