package com.codecool.controller;

import com.codecool.DTO.user.NewUserDTO;
import com.codecool.DTO.user.UserDTO;
import com.codecool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDTO getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserDTO addUser(@RequestBody NewUserDTO newUserDTO) {
        return userService.addUser(newUserDTO);
    }

    @PatchMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.modifyUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
