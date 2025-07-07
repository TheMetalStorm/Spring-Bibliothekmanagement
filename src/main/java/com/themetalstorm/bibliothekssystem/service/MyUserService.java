package com.themetalstorm.bibliothekssystem.service;

import com.themetalstorm.bibliothekssystem.model.User;
import com.themetalstorm.bibliothekssystem.model.UserPrincipal;
import com.themetalstorm.bibliothekssystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);
        if(byUsername == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(byUsername);
    }

    public User registerUser(User user) throws UsernameNotFoundException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
