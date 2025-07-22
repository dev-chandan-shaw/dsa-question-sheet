package com.practice.cheetcode.Exception;

import com.practice.cheetcode.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ Handle custom exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {
        // Create the response body
        ApiResponse<?> body = ApiResponse.failure(ex.getMessage(), HttpStatus.NOT_FOUND);
        // Return a ResponseEntity with the body and the correct status code
        return new ResponseEntity<>(body.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
        ApiResponse<?> body = ApiResponse.failure(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(body.getMessage());
    }


    // ✅ Handle all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        ApiResponse<?> body = ApiResponse.failure("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(body.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}