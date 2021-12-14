package com.example.facSchedule.controllers;

import com.example.facSchedule.service.PickedGroupService;
import com.example.facSchedule.service.StudentService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private PickedGroupService pickedGroupService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/enrollStudent/{idStudent}/{idSubjectGroup}")
    public ResponseEntity enrollStudent(@PathVariable Long idStudent, @PathVariable Long idSubjectGroup) {
        try {
            pickedGroupService.studentEnrollsGroup(idStudent, idSubjectGroup);
            return ResponseEntity.ok("Speciality created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllClasses/{idStudent}")
    public ResponseEntity getAllClasses(@PathVariable Long idStudent) {
        try {
            return ResponseEntity.ok(studentService.getClassesForStudent(idStudent));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllWeekClasses/{idStudent}")
    public ResponseEntity getAllWeekClasses(@PathVariable Long idStudent) {
        try {
            return ResponseEntity.ok(studentService.getClassesForWeekForStudent(idStudent));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getClassesForDay/{idStudent}/{numOfDay}")
    public ResponseEntity getClassesForDay(@PathVariable Long idStudent, @PathVariable int numOfDay) {
        try {
            return ResponseEntity.ok(studentService.getClassesForDay(idStudent, numOfDay));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
