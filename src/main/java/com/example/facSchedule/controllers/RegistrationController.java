package com.example.facSchedule.controllers;


import com.example.facSchedule.entity.Authority;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(StudentEntity student) {
        StudentEntity studentFromDb = studentRepo.findByUsername(student.getUsername());

        if (studentFromDb != null) {
            return "registration";
        }

        student.setEnabled(true);
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setAuthorities(Collections.singleton(Authority.ROLE_ADMIN));
        studentRepo.save(student);

        return "redirect:/login";
    }
}