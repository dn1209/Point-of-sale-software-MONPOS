package com.example.demo.model;

public class ApiResponse {
    private final int status_code;
    private final Object data;

    public ApiResponse(int status_code, Object data) {
        this.data = data;
        this.status_code = status_code;

    }

    public int getStatusCode() {
        return status_code;
    }

    public Object getData() {
        return data;
    }
}
