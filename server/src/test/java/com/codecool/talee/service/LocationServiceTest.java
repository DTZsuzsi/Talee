package com.codecool.talee.service;

import com.codecool.talee.DTO.location.LocationDTO;
import com.codecool.talee.DTO.location.NewLocationDTO;
import com.codecool.talee.DTO.user.UserDTO;
import com.codecool.talee.model.locations.Location;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.users.Role;
import com.codecool.talee.model.users.UserEntity;
import com.codecool.talee.repository.*;
import com.codecool.talee.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
  private JWTUtils jwtUtils;

  @Mock
  private UserRepository userRepository;

  @Mock
  private TagRepository tagRepository;

  @InjectMocks
  private LocationService locationService;

  private Location mockLocation;
  private NewLocationDTO newLocationDTO;
  private UserEntity mockUser;
  private Tag mockTag;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    mockLocation = createMockLocation(1L, "Test Location", "123 Test St", "123-456-7890", "test@test.com");
    mockUser = createMockUser(1L, "testuser");
    mockTag = createMockTag(1L, "Test Tag");

    newLocationDTO = new NewLocationDTO(
            "Test Location",
            "123 Test St",
            "123-456-7890",
            "test@test.com",
            "",
            "",
            "",
            "Description",
            new UserDTO(1L, "Test User", Set.of(), Set.of()),
            List.of(),
            40.7128,
            -74.0060
    );
  }

  @Nested
  class GetLocationTests {

    @Test
    void getAllLocationsTest() {
      when(locationRepository.findAll()).thenReturn(List.of(mockLocation));

      List<LocationDTO> locations = locationService.getAllLocations();

      assertNotNull(locations);
      assertEquals(1, locations.size());
      assertEquals("Test Location", locations.get(0).name());
    }

    @Test
    void getLocationsByTagTest() {
      when(tagRepository.findByName("Test Tag")).thenReturn(Optional.of(mockTag));
      when(locationRepository.findAllByTagsContaining(mockTag)).thenReturn(List.of(mockLocation));

      List<LocationDTO> locations = locationService.getLocationsByTag("Test Tag");

      assertNotNull(locations);
      assertEquals(1, locations.size());
      assertEquals("Test Location", locations.get(0).name());
    }

    @Test
    void getLocationByIdTest() {
      when(locationRepository.findById(1L)).thenReturn(Optional.of(mockLocation));

      LocationDTO locationDTO = locationService.getLocationById(1L);

      assertNotNull(locationDTO);
      assertEquals("Test Location", locationDTO.name());
      assertEquals("123 Test St", locationDTO.address());
    }
  }

  @Nested
  class ModifyLocationTests {

    @Test
    void addLocationTest() {
      String token = "validToken";

      when(jwtUtils.getUsernameFromJwtToken(token)).thenReturn("testuser");
      when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(mockUser));
      when(roleRepository.findByName("ROLE_LOCATION_OWNER")).thenReturn(Optional.of(new Role("ROLE_LOCATION_OWNER")));
      when(locationRepository.save(any(Location.class))).thenReturn(mockLocation);

      boolean result = locationService.addLocation(newLocationDTO, token);

      assertTrue(result);
    }

    @Test
    void updateLocationTest() {
      LocationDTO updatedLocationDTO = new LocationDTO(
              1L, "Updated Location", "456 Updated St", "789-123-4560", "updated@test.com",
              "Updated Description", null, List.of(), Set.of(), 0.0, 0.0, Set.of()
      );

      when(locationRepository.findById(1L)).thenReturn(Optional.of(mockLocation));
      when(locationRepository.save(any(Location.class))).thenReturn(mockLocation);

      boolean result = locationService.updateLocation(updatedLocationDTO);

      assertTrue(result);
      verify(locationRepository, times(1)).save(any(Location.class));
    }
  }

  @Nested
  class DeleteLocationTests {

    @Test
    void deleteLocationTest() {
      when(locationRepository.deleteLocationById(1L)).thenReturn(true);

      assertTrue(locationService.deleteLocation(1L));

      verify(locationRepository, times(1)).deleteLocationById(1L);
    }
  }

  // Helper Methods
  private Location createMockLocation(long id, String name, String address, String phone, String email) {
    Location location = new Location();
    location.setId(id);
    location.setName(name);
    location.setAddress(address);
    location.setPhone(phone);
    location.setEmail(email);
    location.setDescription("Description");
    location.setTags(Set.of());
    return location;
  }

  private UserEntity createMockUser(long id, String username) {
    UserEntity user = new UserEntity();
    user.setId(id);
    user.setUsername(username);
    user.setRoles(Set.of());
    return user;
  }

  private Tag createMockTag(long id, String name) {
    Tag tag = new Tag();
    tag.setId(id);
    tag.setName(name);
    return tag;
  }
}
