package com.example.demo.model.payload.Response;

public class ApiResponse {
    private final int status_code;
    private  Object data;
    private  Object message;

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
