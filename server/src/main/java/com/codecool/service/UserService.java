package com.codecool.service;

import com.codecool.DTO.user.NewUserDTO;
import com.codecool.DTO.user.UserDTO;
import com.codecool.exception.UserNotFoundException;
import com.codecool.mapper.UserMapper;
import com.codecool.model.users.UserEntity;
import com.codecool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().
                map(userMapper::userToUserDTO)
                .toList();
    }

    public UserDTO addUser(NewUserDTO newUserDTO) {
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(newUserDTO.username());

        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return userMapper.userToUserDTO(savedUserEntity);
    }

    public UserDTO modifyUser(UserDTO userDTO) {
        UserEntity existingUserEntity = userRepository.findById(userDTO.id())
                .orElseThrow(() -> new UserNotFoundException(userDTO.id()));

        existingUserEntity.setUsername(userDTO.username());
        existingUserEntity.setRoles(userDTO.roles());

        UserEntity savedUserEntity = userRepository.save(existingUserEntity);
        return userMapper.userToUserDTO(savedUserEntity);
    }

    public boolean deleteUser(long id) {
        return userRepository.deleteUserById(id);
    }
}
