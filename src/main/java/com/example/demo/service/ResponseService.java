package com.example.demo.service;

import java.util.Map;

public class ResponseService {
    public static Object createErrorResponse(String status, String message) {
        // Tạo JSON object cho response thông báo lỗi
        return Map.of("status", status, "message", message);
    }

    public static Object createSuccessResponse(String status, String message) {
        // Tạo JSON object cho response thông báo thành công
        return Map.of("status", status, "message", message);
    }
}
