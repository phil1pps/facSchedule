package com.example.facSchedule.controllers;

import com.example.facSchedule.config.JwtProvider;
import com.example.facSchedule.entity.Users;
import com.example.facSchedule.repository.UsersRepo;
import com.example.facSchedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;



    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody AuthRequest request) {
        Users userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token =
                jwtProvider
                        .generateToken(userEntity.getUsername());
/*        Cookie jwtTokenCookie = new Cookie("user-id", token);*/
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie","user-id="+token);
        return ResponseEntity.ok()
                .headers(headers)
                .body(token);
    }
}