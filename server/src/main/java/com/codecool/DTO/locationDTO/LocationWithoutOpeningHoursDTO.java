package com.codecool.DTO.locationDTO;

import com.codecool.model.users.User;

public record LocationWithoutOpeningHoursDTO (long id, String name, String address, String phone, String email, String description,
                                             User adminUser) {
}
