package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final MyUserService userService;

    @Autowired
    public UserController(MyUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }
}
