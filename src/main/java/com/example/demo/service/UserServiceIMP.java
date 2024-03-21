package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserServiceIMP  {
    UserDetails loadUserByUserName(String userName) throws UserNotFoundException;
}
