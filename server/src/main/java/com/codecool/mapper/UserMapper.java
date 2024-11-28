package com.codecool.mapper;

import com.codecool.DTO.user.UserDTO;
import com.codecool.DTO.user.UserInEventDTO;
import com.codecool.model.users.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper//(uses = {EventMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "events", source = "events")
    UserDTO userToUserDTO(UserEntity userEntity);
    UserInEventDTO userToUserInEventDTO(UserEntity userEntity);}

