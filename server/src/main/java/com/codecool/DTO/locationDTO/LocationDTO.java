package com.codecool.DTO.locationDTO;


import com.codecool.model.users.User;

import java.util.List;


//TODO introduce ContactInfoDTO, use EventDTO (+incl) instead
public record LocationDTO(long id, String name, String address, String phone, String email, String description,
                          User adminUser, List<OpeningHoursDTO> openingHours) {
}
