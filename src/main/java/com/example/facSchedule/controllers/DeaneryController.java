package com.example.facSchedule.controllers;


import com.example.facSchedule.service.ProfessorService;
import com.example.facSchedule.service.SpecialityService;
import com.example.facSchedule.service.StudentService;
import com.example.facSchedule.entity.ProfessorEntity;
import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deanery")
public class DeaneryController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SpecialityService specialityService;
    @Autowired
    private ProfessorService professorService;

    @PostMapping("/addSpeciality")
    public ResponseEntity addSpeciality(@RequestBody SpecialityEntity speciality) {
        try {
            specialityService.addSpeciality(speciality);
            return ResponseEntity.ok("Speciality created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registerStudent")
    public ResponseEntity registerStudent(@RequestBody StudentEntity student, @RequestParam Long specialityId) {
        try {
            studentService.registration(student, specialityId);
            return ResponseEntity.ok("Student created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getOneStudentById/{idStudent}")
    public ResponseEntity getOneStudentById(@PathVariable Long idStudent) {
        try {
            return ResponseEntity.ok(studentService.getOneById(idStudent));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registerProfessor")
    public ResponseEntity registerProfessor(@RequestBody ProfessorEntity professor) {
        try {
            professorService.registration(professor);
            return ResponseEntity.ok("Professor created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getOneProfessorById/{idProfessor}")
    public ResponseEntity getOneProfessorById(@PathVariable Long idProfessor) {
        try {
            return ResponseEntity.ok(professorService.getOneById(idProfessor));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}