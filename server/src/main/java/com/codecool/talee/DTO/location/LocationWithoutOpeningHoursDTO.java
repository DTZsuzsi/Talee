package com.codecool.talee.DTO.location;

import com.codecool.talee.DTO.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record LocationWithoutOpeningHoursDTO (long id, String name, String address, String phone, String email, String description,
                                              @JsonIgnore UserDTO adminUser, double latitude, double longitude) {
}
