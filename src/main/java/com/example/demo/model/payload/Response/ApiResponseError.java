package com.example.demo.model.payload.Response;

public class ApiResponseError {
    private final int status_code;
    private  Object message;

    public ApiResponseError(int status_code, Object message) {
        this.status_code = status_code;
        this.message = message;

    }

    public int getStatusCode() {
        return status_code;
    }

    public Object getMessage() {
        return message;
    }
}
