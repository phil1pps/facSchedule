package com.example.facSchedule.controllers;


import com.example.facSchedule.entity.*;
import com.example.facSchedule.service.*;
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
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectGroupService subjectGroupService;
    @Autowired
    private ClassService classService;


    //TODO Speciality add edit getAll
    @PostMapping("/addSpeciality")
    public ResponseEntity addSpeciality(@RequestBody SpecialityEntity speciality) {
        try {
            specialityService.addSpeciality(speciality);
            return ResponseEntity.ok("Speciality created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editSpeciality/{specialityId}")
    public ResponseEntity addSpeciality(@RequestBody SpecialityEntity speciality, @PathVariable Long specialityId) {
        try {
            specialityService.editSpeciality(specialityId,speciality);
            return ResponseEntity.ok("Speciality edited");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getSpecialities")
    public ResponseEntity getSpecialities() {
        try {
            return ResponseEntity.ok(specialityService.getAllSpeciality());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    //TODO Student add edit getOne getAll(by speciality)
    @PostMapping("/registerStudent")
    public ResponseEntity registerStudent(@RequestBody StudentEntity student, @RequestParam Long specialityId) {
        try {
            studentService.registration(student, specialityId);
            return ResponseEntity.ok("Student created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editStudent/{studentId}/{specialityId}")
    public ResponseEntity editStudent(@RequestBody StudentEntity student, @PathVariable Long studentId, @PathVariable Long specialityId) {
        try {
            studentService.editStudent(studentId ,student , specialityId);
            return ResponseEntity.ok("Student edited");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    ///
    @GetMapping("/getStudentsFromSpeciality/{specialityId}")
    public ResponseEntity getStudentsFromSpeciality(@PathVariable Long specialityId) {
        try {
            return ResponseEntity.ok(studentService.getStudentsFromSpeciality(specialityId));
        }catch (Exception e){
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



    //TODO Professor add edit getOne
    @PostMapping("/registerProfessor")
    public ResponseEntity registerProfessor(@RequestBody ProfessorEntity professor) {
        try {
            professorService.registration(professor);
            return ResponseEntity.ok("Professor created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editProfessor/{idProfessor}")
    public ResponseEntity editProfessor(@RequestBody ProfessorEntity professor, @PathVariable Long idProfessor) {
        try {
            professorService.editProfessor(idProfessor, professor);
            return ResponseEntity.ok("Professor edited");
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

    @GetMapping("/getAllProfessors")
    public ResponseEntity getAllProfessors() {
        try {
            return ResponseEntity.ok(professorService.getAllProfessors());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    //TODO Subject add edit getOne getAll(by speciality)
    @PostMapping("/addSubjectToSpeciality/{specialityId}")
    public ResponseEntity addSubject(@RequestBody SubjectEntity subject, @PathVariable Long specialityId) {
        try {
            subjectService.addSubject(subject,specialityId);
            return ResponseEntity.ok("Subject created");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editSubject/{idSubject}")
    public ResponseEntity editSubject(@RequestBody SubjectEntity subject, @PathVariable Long idSubject) {
        try {
            subjectService.editSubject(idSubject, subject);
            return ResponseEntity.ok("Subject edited");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getOneSubject/{idSubject}")
    public ResponseEntity getOneSubject(@PathVariable Long idSubject) {
        try {
            return ResponseEntity.ok(subjectService.getOneSubject(idSubject));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getSpecialitySubjects/{idSpeciality}")
    public ResponseEntity getSpecialitySubjects(@PathVariable Long idSpeciality) {
        try {
            return ResponseEntity.ok(subjectService.getSpecialitySubjects(idSpeciality));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    //TODO SubjectGroup add edit getOne getAll(by Subject)
    @PostMapping("/addGroupToSubject/{idSubject}/{idProfessor}")
    public ResponseEntity addGroupToSubject(@RequestBody SubjectGroupEntity subjectGroup, @PathVariable Long idSubject, @PathVariable Long idProfessor){
        try {
            ResponseEntity.ok(subjectGroupService.addGroupToSubject(subjectGroup,idSubject,idProfessor));
            return ResponseEntity.ok("Group added");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editSubjectGroup/{idGroup}")
    public ResponseEntity editSubjectGroup(@RequestBody SubjectGroupEntity subjectGroup, @PathVariable Long idGroup){
        try {
            ResponseEntity.ok(subjectGroupService.editSubjectGroup(idGroup,subjectGroup));
            return ResponseEntity.ok("Group edited");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getSubjectGroups/{idSubject}")
    public ResponseEntity getSubjectGroups(@PathVariable Long idSubject){
        try {
            return ResponseEntity.ok(subjectGroupService.getSubjectGroups(idSubject));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getOneSubjectGroup/{subjectGroupId}")
    public ResponseEntity getOneSubjectGroup(@PathVariable Long subjectGroupId){
        try {
            return ResponseEntity.ok(subjectGroupService.getOneSubjectGroup(subjectGroupId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    //TODO Class add edit getOne getAll(by Subject)
    @PostMapping("/addClass/{subjectGroupId}")
    public ResponseEntity addClass(@RequestBody ClassEntity Class, @PathVariable Long subjectGroupId){
        try {
            return ResponseEntity.ok(classService.addClass(Class,subjectGroupId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}