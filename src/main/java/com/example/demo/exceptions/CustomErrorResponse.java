package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorResponse {
    private String message;
    private Integer status;
    private LocalDateTime timestamp;

    public CustomErrorResponse(String message, int status){
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}