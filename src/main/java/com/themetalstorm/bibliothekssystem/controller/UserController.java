package com.themetalstorm.bibliothekssystem.controller;

import com.themetalstorm.bibliothekssystem.model.Role;
import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

//TODO: return Response Entity when appropriate

@RestController
public class UserController {
    private final MyUserService userService;

    @Autowired
    public UserController(MyUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public Page<User> getAllUsers(@RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer size,
                                  @RequestParam(defaultValue = "username") String sortField,
                                  @RequestParam(defaultValue = "ASC") String sortDirection){
        return userService.getAllUsers(page, size, sortField, sortDirection);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user)
    {
        if(user.getRole().equals(Role.ROLE_ADMIN)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            boolean senderIsAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

            if(senderIsAdmin){
                return userService.register(user);
            }
            else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        else{
            return userService.register(user);
        }
    }


    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable String username){
        return userService.deleteByName(username);
    }

}
