package com.example.facSchedule.repository;

import com.example.facSchedule.entity.ProfessorEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepo extends CrudRepository<ProfessorEntity, Long>
{
    ProfessorEntity findByLogin(String login);
    ProfessorEntity findByIdProfessor(Long idProfessor);
}
