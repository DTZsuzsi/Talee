package com.codecool.DTO.location;

import com.codecool.DTO.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record LocationWithoutOpeningHoursDTO (long id, String name, String address, String phone, String email, String description,
                                              @JsonIgnore UserDTO adminUser) {
}
