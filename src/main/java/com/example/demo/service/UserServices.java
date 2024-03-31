package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {
    LocalDate today = LocalDate.now();


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
    public boolean checkingUserStatus(Long id){
        return userRepository.existsByIdAndUserStatusIsZero(id);
    }

    public String getParentUser(Long userName){
        Optional<String> userOptional = userRepository.findUserNameByUserId(userName);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + userName);
        }
        return userOptional.get();
    }
    public void UpdateUserStatus(String email){
        userRepository.updateUserByEmail(email);
    }


    public UserDetails loadUserById(Long userId) {
        Optional<com.example.demo.model.User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with username: " + userId);
        }
        com.example.demo.model.User user = userOptional.get();
        return new CustomUserDetails(user);
    }
    public boolean checkUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public User registerUser(String userName, String password, Store store,String email){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserStatus("0");
        user.setCreated(LocalDate.now());
        user.setLogined(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setUpdated(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setStore(store);
        return userRepository.save(user);
    }

}
