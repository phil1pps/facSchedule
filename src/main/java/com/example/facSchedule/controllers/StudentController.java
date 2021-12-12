package com.example.facSchedule.controllers;

import com.example.facSchedule.service.PickedGroupService;
import com.example.facSchedule.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
