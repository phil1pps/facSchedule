package com.example.facSchedule.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity handleExceptionAlreadyExist(AlreadyExistException e) {
        return ResponseEntity.badRequest().body("Кидає з глобального обробника ексепшн: " + e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleExceptionNotFound(NotFoundException e) {
        return ResponseEntity.badRequest().body("Кидає з глобального обробника ексепшн: " + e.getMessage());
    }

}