package com.example.demo.model.payload.Response;

import lombok.Data;

@Data
public class ApiResponse {
    private String error_code;
    private String message;


    public ApiResponse(String message, String error_code) {
        this.error_code = "0";
        this.message = message;
    }
}
