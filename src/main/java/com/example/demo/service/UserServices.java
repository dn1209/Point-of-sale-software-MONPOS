package com.example.demo.service;

import com.example.demo.SecurityConfig;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Store;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {
    LocalDate today = LocalDate.now();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
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
    public User getUserById(Long id){
        Optional<User> userOptional = userRepository.findUserById(id);
        if( userOptional.isEmpty()){
            throw new UserNotFoundException("Store not found with username: " + id);
        }
        User user = userOptional.get();
        return user;
    }
    public Long getStoreIdByUserId(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7); // Bỏ phần "Bearer " từ token
            Long userId = jwtTokenProvider.getUserIdFromJWT(token);
            Optional<Long> storeId = userRepository.findStoreIdByUserId(userId);
            if(storeId.isEmpty()){
                throw new UserNotFoundException("khong tim thay sotreid ");
            }
            return storeId.get();
        }
        return 1L;
    }
    public Long getUserIdByToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        String token = bearerToken.substring(7); // Bỏ phần "Bearer " từ token
        Long userId = jwtTokenProvider.getUserIdFromJWT(token);
        return userId;
    }
    @Transactional
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
        User user = new User(
                userName,
                securityConfig.passwordEncoder().encode(password),
                email,
                "0",
                LocalDate.now(),
                Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                store,
                Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant())
                );
        return userRepository.save(user);
    }

}
