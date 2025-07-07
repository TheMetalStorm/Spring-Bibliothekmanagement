package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final MyUserService userDetailsService;

    @Autowired
    public UserController(MyUserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userDetailsService.registerUser(user);

    }
}
