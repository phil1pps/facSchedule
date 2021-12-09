package com.example.facSchedule.service;

import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.SubjectEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.model.SubjectModel;
import com.example.facSchedule.repository.SpecialityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepo specialityRepo;

    public SpecialityEntity addSpeciality(SpecialityEntity speciality) throws AlreadyExistException {
        if (specialityRepo.findBySpecialityName(speciality.getSpecialityName()) != null)
            throw new AlreadyExistException("Така спеціальність вже існує!");
        return specialityRepo.save(speciality);
    }

    public List<SpecialityModel> getAllSpeciality() throws NotFoundException {
        List<SpecialityModel> list =  StreamSupport.stream(specialityRepo.findAll().spliterator(), false).map(SpecialityModel::toModel)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new NotFoundException("No specialities!");
        }
        return list;
    }

    public SpecialityEntity editSpeciality(Long idSpeciality, SpecialityEntity newSpeciality) throws AlreadyExistException, NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(idSpeciality);
        if(speciality==null) throw new NotFoundException("No such specialities with this id");
        String newSpecialityName = newSpeciality.getSpecialityName();
        if((!speciality.getSpecialityName().equals(newSpecialityName)) &&
                (specialityRepo.countAllBySpecialityName(newSpecialityName)!=0)) throw new AlreadyExistException("Speciality with this name already exist!");

        speciality.setSpecialityName(newSpecialityName);
        return specialityRepo.save(speciality);
    }

    public Long delete(Long id) {
        specialityRepo.deleteById(id);
        return id;
    }

    public SpecialityModel getOneSpeciality(Long idSpeciality) throws NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(idSpeciality);
        if(speciality==null) throw new NotFoundException("No such specialities with this id");
        return speciality.toModel();
    }

}