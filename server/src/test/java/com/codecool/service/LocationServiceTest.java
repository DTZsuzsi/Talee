package com.codecool.service;

import com.codecool.mapper.LocationMapper;
import com.codecool.mapper.TagMapper;
import com.codecool.mapper.UserMapper;
import com.codecool.repository.LocationRepository;
import com.codecool.repository.RoleRepository;
import com.codecool.repository.TagCategoryRepository;
import com.codecool.repository.UserRepository;
import com.codecool.security.JWTUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

  @Test
  void whenLocationsGetAllLocations_returnsListOfLocationDTOs() {

  }

  @Test
  void whenNoLocationsGetAllLocations_returnsEmptyList() {
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