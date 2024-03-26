package com.example.demo.exception;

import com.example.demo.model.payload.Response.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseError> handleHttpMessageNotReadableException(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        ApiResponseError response = new ApiResponseError(HttpStatus.BAD_REQUEST.value(), "Định dạng dữ liệu không hợp lệ.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({ org.springframework.security.access.AccessDeniedException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponseError> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex) {
        ApiResponseError response = new ApiResponseError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
