package com.example.facSchedule.controllers;


import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/getAllClasses/{idProfessor}")
    public ResponseEntity getClassesForProfessor(@PathVariable Long idProfessor) {
        try {
            return ResponseEntity.ok(professorService.getClassesForProfessor(idProfessor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllWeekClasses/{idProfessor}")
    public ResponseEntity getClassesForWeekForProfessor(@PathVariable Long idProfessor) {
        try {
            return ResponseEntity.ok(professorService.getClassesForWeekForProfessor(idProfessor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
