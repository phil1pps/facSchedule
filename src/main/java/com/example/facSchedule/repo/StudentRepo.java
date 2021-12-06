package com.example.facSchedule.repo;

import com.example.facSchedule.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<StudentEntity,Long> {

}