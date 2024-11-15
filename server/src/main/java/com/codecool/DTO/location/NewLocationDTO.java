package com.codecool.DTO.location;


import com.codecool.DTO.user.UserDTO;
import com.codecool.model.users.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//TODO use ContactInfoDTO, OpeningHoursDTO instead?
public record NewLocationDTO(String name, String address, String phone, String email, String website, String facebook,
                             String instagram, String description,  @JsonManagedReference UserDTO adminUser) {
}
