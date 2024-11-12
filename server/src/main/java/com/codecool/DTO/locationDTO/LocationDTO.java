package com.codecool.DTO.locationDTO;

import com.codecool.model.User;

//TODO use ContactInfoDTO, OpeningHoursDTO and EventDTO instead?
public record LocationDTO(int id, String name, String address, String phone, String email, String description, User adminUser) {
}
