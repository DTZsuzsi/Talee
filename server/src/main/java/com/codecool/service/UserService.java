package com.codecool.service;

import com.codecool.DTO.user.NewUserDTO;
import com.codecool.DTO.user.UserDTO;
import com.codecool.exception.UserNotFoundException;
import com.codecool.mapper.UserMapper;
import com.codecool.model.users.User;
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
        List<User> users = userRepository.findAll();

        return users.stream().
                map(userMapper::userToUserDTO)
                .toList();
    }

    public UserDTO addUser(NewUserDTO newUserDTO) {
        User newUser = new User();
        newUser.setUsername(newUserDTO.username());

        User savedUser = userRepository.save(newUser);
        return userMapper.userToUserDTO(savedUser);
    }

    public UserDTO modifyUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.id())
                .orElseThrow(() -> new UserNotFoundException(userDTO.id()));

        existingUser.setUsername(userDTO.username());
        existingUser.setRole(userDTO.role());

        User savedUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(savedUser);
    }

    public boolean deleteUser(long id) {
        return userRepository.deleteUserById(id);
    }
}
