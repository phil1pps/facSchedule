package com.example.facSchedule.service;

import com.example.facSchedule.entity.Authority;
import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.Users;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.model.StudentModel;
import com.example.facSchedule.repository.SpecialityRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SpecialityRepo specialityRepo;
    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public StudentEntity registration (StudentEntity student, Long specialityId) throws AlreadyExistException, NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        if(speciality == null)throw new NotFoundException("No speciality");
        student.setSpeciality(speciality);
        Users userFromBD = userRepo.findByUsername(student.getUsername());
        if(userFromBD != null)
            throw new AlreadyExistException("This username already registered!");

        student.setEnabled(true);
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setAuthorities(Collections.singleton(Authority.ROLE_STUDENT));
        return studentRepo.save(student);
    }

    public StudentEntity editStudent(Long idStudent, StudentEntity newStudent, Long specialityId) throws NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        if(speciality == null)throw new NotFoundException("No speciality with this id");
        StudentEntity student = studentRepo.findById(idStudent).get();
        if(student==null)throw new NotFoundException("No student with this id");
        student.setStudentName(newStudent.getStudentName());
        student.setCourse(newStudent.getCourse());
        student.setSpeciality(speciality);
        return studentRepo.save(student);
    }

    public List<StudentModel> getStudentsFromSpeciality(Long specialityId) throws NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        if(speciality == null) throw new NotFoundException("No speciality with this id");
        List<StudentModel> list =  StreamSupport.stream(studentRepo.findAllBySpeciality(speciality).spliterator(), false).map(StudentModel::toModel)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new NotFoundException("No specialities!");
        }
        return list;
    }

    public StudentModel getOneById (Long idStudent){
        StudentEntity student = studentRepo.findById(idStudent).get();
        return student.toModel();
    }


}

