package com.codecool.DTO.location;


import com.codecool.DTO.user.UserDTO;
import com.codecool.model.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

//TODO introduce ContactInfoDTO, use EventDTO (+incl) instead
public record LocationDTO(long id, String name, String address, String phone, String email, String description, @JsonIgnore UserDTO adminUser, List<OpeningHoursDTO> openingHours) {
}
