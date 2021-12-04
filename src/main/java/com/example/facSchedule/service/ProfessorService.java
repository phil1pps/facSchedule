package com.example.facSchedule.service;

import com.example.facSchedule.entity.ProfessorEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.ProfessorModel;
import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.repository.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepo professorRepo;


    //TODO rewrite

    public ProfessorEntity registration (ProfessorEntity professor) throws AlreadyExistException {
        if (professorRepo.findByLogin(professor.getLogin()) != null) throw new AlreadyExistException("Професор з таким логіном вже існує");
        return professorRepo.save(professor);
    }

    public Long delete(Long id) {
        professorRepo.deleteById(id);
        return id;
    }

    public List<ProfessorModel> getAllProfessors() throws NotFoundException {
        List<ProfessorModel> list =  StreamSupport.stream(professorRepo.findAll().spliterator(), false).map(ProfessorModel::toModel)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NotFoundException("No professors!");
        }

        return list;
    }


}