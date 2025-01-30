package com.codecool.talee.service;

import com.codecool.talee.DTO.user.NewUserDTO;
import com.codecool.talee.DTO.user.UserDTO;
import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.mapper.UserMapper;
import com.codecool.talee.model.users.Role;
import com.codecool.talee.model.users.UserEntity;
import com.codecool.talee.repository.RoleRepository;
import com.codecool.talee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO getUserById(long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().
                map(userMapper::userToUserDTO)
                .toList();
    }

    public UserDTO createUser(NewUserDTO newUserDTO) {
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        UserEntity newUserEntity = new UserEntity(
                newUserDTO.username(),
                passwordEncoder.encode(newUserDTO.password()),
                new HashSet<>(),
                Set.of(role)
        );

        UserEntity savedUserEntity = userRepository.save(newUserEntity);
        return userMapper.userToUserDTO(savedUserEntity);
    }

    public long modifyUser(UserDTO userDTO) {
        return userRepository.findById(userDTO.id())
                .map(existingUser -> updateUser(existingUser, userDTO))
                .map(userRepository::save)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new EntityNotFoundException("User", userDTO.id())).id();
    }

    private UserEntity updateUser(UserEntity existingUser, UserDTO userDTO) {
        existingUser.setUsername(userDTO.username());
        existingUser.setRoles(userDTO.roles());
        return existingUser;
    }

    public boolean deleteUser(long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                })
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }
}
