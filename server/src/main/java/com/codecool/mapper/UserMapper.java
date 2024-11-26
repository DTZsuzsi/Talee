package com.codecool.mapper;

import com.codecool.DTO.user.UserDTO;
import com.codecool.model.users.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper//(uses = {EventMapper.class}, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "events", source = "events")
    UserDTO userToUserDTO(UserEntity userEntity);

//    @Mapping(target = "events", ignore = true)
//    User userDTOToUser(UserDTO userDTO);
}
