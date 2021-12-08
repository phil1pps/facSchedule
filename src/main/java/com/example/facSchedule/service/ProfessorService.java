package com.example.facSchedule.service;

import com.example.facSchedule.entity.ProfessorEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.repository.ProfessorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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