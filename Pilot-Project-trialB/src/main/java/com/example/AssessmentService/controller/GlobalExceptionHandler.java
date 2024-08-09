package com.example.AssessmentService.controller;

import com.example.AssessmentService.exception.InvalidQuestionOrSetIdException;
import com.example.AssessmentService.exception.SetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SetNotFoundException.class)
    public ResponseEntity<String> handleSetNotFoundException(SetNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK); // 200 with a custom message
    }

    @ExceptionHandler(InvalidQuestionOrSetIdException.class)
    public ResponseEntity<String> handleInvalidQuestionOrSetIdException(InvalidQuestionOrSetIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
    }
}
