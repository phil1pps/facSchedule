package com.example.facSchedule.controllers;



import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
}