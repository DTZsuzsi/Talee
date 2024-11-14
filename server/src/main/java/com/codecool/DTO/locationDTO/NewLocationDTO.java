package com.codecool.DTO.locationDTO;


import com.codecool.model.users.User;

import java.util.List;

//TODO introduce ContactInfoDTO
public record NewLocationDTO(String name, String address, String phone, String email, String website, String facebook,
                             String instagram, String description, User adminUser, List<NewOpeningHoursWithoutLocationDTO> openingHours) {
}
