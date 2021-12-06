package com.example.facSchedule.Service;

import com.example.facSchedule.entity.ProfessorEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.ProfessorModel;
import com.example.facSchedule.repo.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepo professorRepo;


    ///TODO rewrite

    public ProfessorEntity registration (ProfessorEntity professor) throws AlreadyExistException {
        return professorRepo.save(professor);
    }

    public Long delete(Long id) {
        professorRepo.deleteById(id);
        return id;
    }

    public ProfessorEntity getOneById (Long idProfessor){
        ProfessorEntity professor = professorRepo.findById(idProfessor).get();
        return professor;
    }

   /* public List<ProfessorModel> getAllProfessors() throws NotFoundException {
        List<ProfessorModel> list =  StreamSupport.stream(professorRepo.findAll().spliterator(), false).map(ProfessorModel::toModel)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new NotFoundException("No professors!");
        }
        return list;
    }*/


}