package com.example.facSchedule.service;

import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.repository.SpecialityRepo;
import com.example.facSchedule.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SpecialityRepo specialityRepo;


    public StudentEntity registration (StudentEntity student, Long specialityId){
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        student.setSpeciality(speciality);
        return studentRepo.save(student);
    }

    public StudentEntity getOneById (Long idStudent){
        StudentEntity student = studentRepo.findById(idStudent).get();
        return student;
    }


}