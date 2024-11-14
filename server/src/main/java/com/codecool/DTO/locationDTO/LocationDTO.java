package com.codecool.DTO.locationDTO;


import com.codecool.model.users.User;

//TODO introduce ContactInfoDTO, use EventDTO (+incl) instead
public record LocationDTO(long id, String name, String address, String phone, String email, String description,
                          User adminUser, OpeningHoursDTO openingHours) {
}
