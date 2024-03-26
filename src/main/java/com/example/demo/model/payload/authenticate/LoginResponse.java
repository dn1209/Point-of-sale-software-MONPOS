package com.example.demo.model.payload.authenticate;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.User;
import com.example.demo.model.payload.UserData;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class LoginResponse {
    private String error_code;
    private String message;
    private UserData data;


    public LoginResponse(String message, UserData data) {
        this.error_code = "0";
        this.message = message;
        this.data = data;
    }}
