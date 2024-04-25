package com.example.demo.model.payload.Response;

import lombok.Data;

@Data
public class ErrorResponse {
    private int error_code;
    private String message;

    public ErrorResponse(int error_code, String message) {
        this.error_code = error_code;
        this.message = message;
    }

    // Getters và setters
}
