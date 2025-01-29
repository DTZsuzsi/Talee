package com.codecool.talee.DTO.location;


import com.codecool.talee.DTO.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

public record NewLocationDTO(String name, String address, String phone, String email, String website, String facebook,
                             String instagram, String description, @JsonManagedReference UserDTO adminUser, List<NewOpeningHoursWithoutLocationDTO> openingHours, double latitude, double longitude) {
}
