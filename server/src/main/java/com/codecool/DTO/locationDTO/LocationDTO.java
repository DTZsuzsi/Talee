package com.codecool.DTO.locationDTO;


import com.codecool.model.users.User;

//TODO use ContactInfoDTO, OpeningHoursDTO and EventDTO instead
public record LocationDTO(long id, String name, String address, String phone, String email, String description, User adminUser) {
}
