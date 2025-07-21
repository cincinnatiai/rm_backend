package com.example.demo.exceptions;


public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(String message) {
        super("This is the message sent in the exception");
    }
}
