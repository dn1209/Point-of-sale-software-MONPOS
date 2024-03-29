package com.example.demo.model.payload.authenticate;

import com.example.demo.model.payload.UserData;
import lombok.Data;

@Data
public class RegisterReponse {
    private String error_code;
    private String message;


    public RegisterReponse(String message, String error_code) {
        this.error_code = "0";
        this.message = message;
    }
}
