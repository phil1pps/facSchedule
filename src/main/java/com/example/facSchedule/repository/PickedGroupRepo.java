package com.example.facSchedule.repository;

import com.example.facSchedule.entity.PickedGroupEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.SubjectGroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface PickedGroupRepo extends CrudRepository<PickedGroupEntity, Long>
{
    PickedGroupEntity findByStudentAndSubjectGroup(StudentEntity student, SubjectGroupEntity subjectGroup);
    Iterable<PickedGroupEntity> findAllByStudent(StudentEntity student);
}
