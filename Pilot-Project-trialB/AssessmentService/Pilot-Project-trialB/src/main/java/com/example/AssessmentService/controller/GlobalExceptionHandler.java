package com.example.AssessmentService.controller;

import com.example.AssessmentService.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private  final String setAlreadyExists = "Set already exists";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(
            ResourceNotFoundException exception) {
        return ResponseEntity.ok(exception.getMessage()); // 200 with a custom message
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityException(
            DataIntegrityViolationException exception) {

        return ResponseEntity.ok(setAlreadyExists);
    }
}
