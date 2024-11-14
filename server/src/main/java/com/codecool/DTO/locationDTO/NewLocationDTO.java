package com.codecool.DTO.locationDTO;


import com.codecool.model.location.OpeningHours;
import com.codecool.model.users.User;

//TODO introduce ContactInfoDTO
public record NewLocationDTO(String name, String address, String phone, String email, String website, String facebook,
                             String instagram, String description, User adminUser, NewOpeningHoursDTO openingHours) {
}
