package com.codecool.DTO.location;


import com.codecool.DTO.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

//TODO introduce ContactInfoDTO
public record NewLocationDTO(String name, String address, String phone, String email, String website, String facebook,
                             String instagram, String description, @JsonManagedReference UserDTO adminUser, List<NewOpeningHoursWithoutLocationDTO> openingHours, double latitude, double longitude) {
}
