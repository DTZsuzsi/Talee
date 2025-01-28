package com.codecool.talee.mapper;

import com.codecool.talee.DTO.user.UserDTO;
import com.codecool.talee.DTO.user.UserInEventDTO;
import com.codecool.talee.model.users.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "events", source = "events")
    UserDTO userToUserDTO(UserEntity userEntity);
    UserInEventDTO userToUserInEventDTO(UserEntity userEntity);}

