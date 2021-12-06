package com.example.facSchedule.repo;


import com.example.facSchedule.entity.SpecialityEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepo extends CrudRepository<SpecialityEntity, Long>
{
    SpecialityEntity findBySpecialityName(String specialityName);
    SpecialityEntity findByIdSpeciality(Long idSpeciality);
}
