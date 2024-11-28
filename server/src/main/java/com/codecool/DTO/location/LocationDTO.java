package com.codecool.DTO.location;


import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.DTO.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

//TODO introduce ContactInfoDTO, use EventDTO (+incl) instead
public record LocationDTO(long id, String name, String address, String phone, String email, String description, UserDTO adminUser, List<OpeningHoursDTO> openingHours, List<TaginFrontendDTO> locationTags, double latitude, double longitude) {
}
