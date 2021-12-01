package com.example.facSchedule.repository;

import com.example.facSchedule.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<StudentEntity, Long>
{
    StudentEntity findByLogin(String login);
    StudentEntity findByIdStudent(Long idStudent);
}
