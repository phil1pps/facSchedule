package com.example.facSchedule.repository;

import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<StudentEntity,Long> {
    StudentEntity findByUsername(String username);
    Iterable<StudentEntity> findAllBySpeciality(SpecialityEntity speciality);
}