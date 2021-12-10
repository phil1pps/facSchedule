package com.example.facSchedule.service;

import com.example.facSchedule.entity.Authority;
import com.example.facSchedule.entity.ProfessorEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.Users;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.ProfessorModel;
import com.example.facSchedule.repository.ProfessorRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepo professorRepo;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UsersRepo userRepo;

    public ProfessorEntity registration (ProfessorEntity professor) throws AlreadyExistException {

        Users userFromBD = userRepo.findByUsername(professor.getUsername());
        if(userFromBD != null)
            throw new AlreadyExistException("This username already registered!");

        professor.setEnabled(true);
        professor.setPassword(bCryptPasswordEncoder.encode(professor.getPassword()));
        professor.setAuthorities(Collections.singleton(Authority.ROLE_PROFESSOR));

        return professorRepo.save(professor);
    }

    public ProfessorEntity editProfessor(Long idProfessor, ProfessorEntity newProfessor) throws NotFoundException {
        ProfessorEntity professor = professorRepo.findById(idProfessor).get();
        if(professor==null)throw new NotFoundException("No professor with this id");
        professor.setProfessorName(newProfessor.getProfessorName());
        return professorRepo.save(professor);
    }

    public Long delete(Long id) {
        professorRepo.deleteById(id);
        return id;
    }

    public ProfessorModel getOneById (Long idProfessor) throws NotFoundException {
        ProfessorEntity professor = professorRepo.findById(idProfessor).get();
        if(professor==null)throw new NotFoundException("No professor with this id");
        return professor.toModel();
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