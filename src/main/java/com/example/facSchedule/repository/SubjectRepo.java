package com.example.facSchedule.repository;

import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepo extends CrudRepository<SubjectEntity, Long>
{
    SubjectEntity findByIdSubject(Long id);
    SubjectEntity findBySpecialityAndSubjectName(SpecialityEntity speciality, String subjectName);
    Iterable <SubjectEntity> findAllBySpeciality(SpecialityEntity speciality);
    Iterable <SubjectEntity> findAllByCourseAndSpeciality(Integer course, SpecialityEntity speciality);

    long countAllBySubjectNameAndSpeciality( String subjectName, SpecialityEntity speciality);
}

