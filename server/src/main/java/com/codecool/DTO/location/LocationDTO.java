package com.codecool.DTO.location;


import com.codecool.model.users.User;

//TODO use ContactInfoDTO, OpeningHoursDTO and EventDTO instead?
public record LocationDTO(int id, String name, String address, String phone, String email, String description, User adminUser) {
}
