package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final MyUserService userService;

    @Autowired
    public UserController(MyUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "username") String sortField,
                                  @RequestParam(defaultValue = "ASC") String sortDirection){
        return userService.getAllUsers(page, size, sortField, sortDirection);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    //TODO: PUT to update User

    @DeleteMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username){
        return userService.deleteByName(username);
    }

}
