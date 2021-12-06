package com.example.facSchedule.Service;

import com.example.facSchedule.entity.SpecialityEntity;

import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.repo.SpecialityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepo specialityRepo;

    public SpecialityEntity addSpeciality(SpecialityEntity speciality) throws AlreadyExistException {
        if (specialityRepo.findBySpecialityName(speciality.getSpecialityName()) != null)
            throw new AlreadyExistException("Така спеціальність вже існує!");
        return specialityRepo.save(speciality);
    }

    public Long delete(Long id) {
        specialityRepo.deleteById(id);
        return id;
    }

}