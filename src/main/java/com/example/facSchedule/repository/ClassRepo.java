package com.example.facSchedule.repository;

import com.example.facSchedule.entity.ClassEntity;
import com.example.facSchedule.entity.SubjectGroupEntity;
import org.springframework.data.repository.CrudRepository;


public interface ClassRepo extends CrudRepository<ClassEntity, Long>
{
    Iterable<ClassEntity> findAllBySubjectGroup(SubjectGroupEntity subjectGroup);
}
