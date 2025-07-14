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

import org.springframework.http.ResponseEntity;

@RestController
public class UserController {
    private final MyUserService userService;

    @Autowired
    public UserController(MyUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer size,
                                  @RequestParam(defaultValue = "username") String sortField,
                                  @RequestParam(defaultValue = "ASC") String sortDirection){
        return new ResponseEntity<>(userService.getAllUsers(page, size, sortField, sortDirection), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //TODO: move logic to service
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user)
    {
        if(user.getRole().equals(Role.ROLE_ADMIN)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            boolean senderIsAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

            if(senderIsAdmin){
                return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
            }
            else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        else{
            return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
        }
    }


        @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        return new ResponseEntity<>(userService.verify(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        return new ResponseEntity<>(userService.updateUser(id, userDetails), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteUser/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username){
        return new ResponseEntity<>(userService.deleteByName(username), HttpStatus.OK);
    }

}
