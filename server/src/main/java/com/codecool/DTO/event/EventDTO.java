package com.codecool.DTO.event;

import com.codecool.DTO.location.LocationInEvent;
import com.codecool.DTO.tag.TagDTO;
import com.codecool.model.users.User;

import java.time.LocalDate;
import java.util.List;

//public record EventDTO(int id, LocalDate date, String name, String description, @JsonIgnore LocationDTO location, @JsonIgnore Set<UserDTO> users, String owner, String size, @JsonIgnore Set<Tag> tags, String status, Timestamp timestamp) {
//}
public record EventDTO(int id, LocalDate date, String name, String description, LocationInEvent location, List<User> users, String owner, String size, List<TagDTO> tags, String status) {
}