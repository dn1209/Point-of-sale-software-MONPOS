package com.example.demo.handleEx;

import com.example.demo.exception.JwtInvalidException;
import com.example.demo.model.payload.Response.ErrorResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ComponentScan
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtInvalidException.class)
    public ResponseEntity<Object> handleJwtInvalidException(JwtInvalidException ex) {
        // Xử lý khi JWT không hợp lệ
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, ex.getMessage()));
    }

    // Các ExceptionHandler khác nếu cần
}

