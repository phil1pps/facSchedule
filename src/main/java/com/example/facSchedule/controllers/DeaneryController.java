package com.example.facSchedule.controllers;


import com.example.facSchedule.entity.*;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/deanery")
public class DeaneryController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private SpecialityService specialityService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectGroupService subjectGroupService;
    @Autowired
    private ClassService classService;

    @PostMapping("/addSpeciality")
    public ResponseEntity addSpeciality(@Valid @RequestBody SpecialityEntity speciality) throws AlreadyExistException {
            specialityService.addSpeciality(speciality);
            return ResponseEntity.ok("Speciality created");
    }

    @PostMapping("/addSubjectToSpeciality/{specialityId}")
    public ResponseEntity addSubjectToSpeciality(@Valid @RequestBody SubjectEntity subject, @PathVariable Long specialityId) {
        try {
            subjectService.addSubject(subject,specialityId);
            return ResponseEntity.ok("Subject added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/registerProfessor")
    public ResponseEntity registerProfessor(@RequestBody ProfessorEntity professor) throws AlreadyExistException{
            professorService.registration(professor);
            return ResponseEntity.ok("Professor created");
    }

    @PostMapping("/addGroupSubject")
    public ResponseEntity addGroupSubject(@RequestBody SubjectGroupEntity subjectGroup,  @RequestParam Long idSubject,  @RequestParam Long idProfessor) throws AlreadyExistException, NotFoundException {
        subjectGroupService.addGroupToSubject(subjectGroup,idSubject,idProfessor);
        return ResponseEntity.ok("Group added to subject");
    }

    @PostMapping("/registerStudent")
    public ResponseEntity registerStudent(@RequestBody StudentEntity student, @RequestParam Long specialityId) throws NotFoundException, AlreadyExistException {
        studentService.registration(student, specialityId);
        return ResponseEntity.ok("Student created");
    }





    @PostMapping("/addClass")
    public ResponseEntity addClass(@RequestBody ClassEntity classEntity, @RequestParam Long subjectGroupId) throws AlreadyExistException, NotFoundException {
        classService.addClass(classEntity,subjectGroupId);
        return ResponseEntity.ok("Class added for group");
    }

    @GetMapping("/getOneStudentById/{idStudent}")
    public ResponseEntity getOneStudentById(@PathVariable Long idStudent) {
        try {
            return ResponseEntity.ok(studentService.getOneById(idStudent));
        }catch (Exception e){
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