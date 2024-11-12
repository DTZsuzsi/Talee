package com.codecool.service;

import com.codecool.DTO.user.NewUserDTO;
import com.codecool.DTO.user.UserDTO;
import com.codecool.exceptions.UserNotFoundException;
import com.codecool.model.users.User;
import com.codecool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(long id) {
        return userRepository.findById(id)
                .map(this::makeUserDTO)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().
                map(this::makeUserDTO)
                .toList();
    }

    public UserDTO addUser(NewUserDTO newUserDTO) {
        User newUser = new User();
        newUser.username(newUserDTO.username());

        User savedUser = userRepository.save(newUser);
        return makeUserDTO(savedUser);
    }

    public UserDTO modifyUser(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.id())
                .orElseThrow(() -> new UserNotFoundException(userDTO.id()));

        existingUser.username(userDTO.username());
        existingUser.role(userDTO.role());

        User savedUser = userRepository.save(existingUser);
        return makeUserDTO(savedUser);
    }

    public boolean deleteUser(long id) {
        return userRepository.deleteUserById(id);
    }

    private UserDTO makeUserDTO(User user) {
        return new UserDTO(
                user.id(),
                user.username(),
                user.events(),
                user.role()
        );
    }
}
