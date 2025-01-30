package com.codecool.talee.controller;

import com.codecool.talee.DTO.user.NewUserDTO;
import com.codecool.talee.DTO.user.UserDTO;
import com.codecool.talee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody NewUserDTO newUserDTO) {
        return new ResponseEntity<>(userService.createUser(newUserDTO), HttpStatus.CREATED);
    }

    @PatchMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.modifyUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable long userId) {
        return userService.deleteUser(userId);
    }
}
