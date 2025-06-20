package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleCharacterNotFound(CharacterNotFoundException characterNotFoundException){
        CustomErrorResponse error = new CustomErrorResponse(characterNotFoundException.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertiesIncompleteException.class)
    public ResponseEntity<CustomErrorResponse> handlePropertiesIncomplete(PropertiesIncompleteException propertiesIncompleteException){
        CustomErrorResponse error = new CustomErrorResponse(propertiesIncompleteException.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGeneric(Exception exception) {
        exception.printStackTrace();
        CustomErrorResponse error = new CustomErrorResponse(
                ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
