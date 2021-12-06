package com.example.facSchedule.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;


@Controller

public class MainController {
    @GetMapping("/login")
    public String greeting(Map<String, Object> model) {
        return "login";
    }

}
