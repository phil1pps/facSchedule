package com.example.facSchedule.repository;


import com.example.facSchedule.entity.SpecialityEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepo extends CrudRepository<SpecialityEntity, Long>
{
    SpecialityEntity findBySpecialityName(String specialityName);
    SpecialityEntity findByIdSpeciality(Long idSpeciality);

    long countAllBySpecialityName(String specialityName);
}
