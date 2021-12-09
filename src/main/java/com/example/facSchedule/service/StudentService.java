package com.example.facSchedule.service;

import com.example.facSchedule.entity.Authority;
import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.Users;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.repository.SpecialityRepo;
import com.example.facSchedule.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SpecialityRepo specialityRepo;
    @Autowired
    private StudentRepo userRepo;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public StudentEntity registration (StudentEntity student, Long specialityId) throws AlreadyExistException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        student.setSpeciality(speciality);

        //todo speciality check
        Users studentFromDb = userRepo.findByUsername(student.getUsername());
        if(studentFromDb != null)
            throw new AlreadyExistException("This username already registered!");

        student.setEnabled(true);
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setAuthorities(Collections.singleton(Authority.ROLE_STUDENT));
        return studentRepo.save(student);
    }

    public StudentEntity getOneById (Long idStudent){
        StudentEntity student = studentRepo.findById(idStudent).get();
        return student;
    }


}

