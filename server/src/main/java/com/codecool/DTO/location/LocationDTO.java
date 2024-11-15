package com.codecool.DTO.location;


import com.codecool.DTO.user.UserDTO;
import com.codecool.model.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//TODO use ContactInfoDTO, OpeningHoursDTO and EventDTO instead?
public record LocationDTO(int id, String name, String address, String phone, String email, String description, @JsonIgnore UserDTO adminUser) {
}
