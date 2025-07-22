package com.practice.cheetcode.Exception;


import com.practice.cheetcode.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Handle custom exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<?> handleResourceNotFound(ResourceNotFoundException ex) {
        return ApiResponse.failure(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    // ✅ Handle all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception ex) {
        ex.printStackTrace();
        return ApiResponse.failure("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}