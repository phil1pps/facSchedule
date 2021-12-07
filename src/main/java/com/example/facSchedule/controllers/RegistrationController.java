package com.example.facSchedule.controllers;


import com.example.facSchedule.entity.Role;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.Users;
import com.example.facSchedule.repo.StudentRepo;
import com.example.facSchedule.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private StudentRepo studentRepo;

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
        student.setRoles(Collections.singleton(Role.ADMIN));
        //student.setPassword("{noop}"+student.getPassword());
        studentRepo.save(student);

        return "redirect:/login";
    }
}