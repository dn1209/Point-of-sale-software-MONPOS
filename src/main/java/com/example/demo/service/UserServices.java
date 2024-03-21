package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UserNotFoundException {
        Optional<com.example.demo.model.User> userOptional = userRepository.findUserByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + userName);
        }
        com.example.demo.model.User user = userOptional.get();
        return new CustomUserDetails(user);
    }


    public UserDetails loadUserById(Long userId) {
        Optional<com.example.demo.model.User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + userId);
        }
        com.example.demo.model.User user = userOptional.get();
        return new CustomUserDetails(user);
    }
}
