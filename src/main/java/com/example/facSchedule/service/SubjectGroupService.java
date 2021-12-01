package com.example.facSchedule.service;

import com.example.facSchedule.entity.ProfessorEntity;
import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.SubjectEntity;
import com.example.facSchedule.entity.SubjectGroupEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.SubjectGroupModel;
import com.example.facSchedule.model.SubjectModel;
import com.example.facSchedule.repository.ProfessorRepo;
import com.example.facSchedule.repository.SubjectGroupRepo;
import com.example.facSchedule.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectGroupService {


@Autowired
private SubjectGroupRepo subjectGroupRepo;

@Autowired
private ProfessorRepo professorRepo;

@Autowired
private SubjectRepo subjectRepo;

    public SubjectGroupEntity addGroupToSubject(SubjectGroupEntity subjectGroup, Long idSubject, Long idProfessor) throws AlreadyExistException, NotFoundException {
        ProfessorEntity professor = professorRepo.findByIdProfessor(idProfessor);
        SubjectEntity subject = subjectRepo.findByIdSubject(idSubject);
        if (professor == null) throw new NotFoundException("No such professor!");
        if (subject == null) throw new NotFoundException("No such subject!");
        if (subjectGroupRepo.findByGroupNameAndSubject(subjectGroup.getGroupName(), subject) != null) throw new AlreadyExistException("Group with this name for this subject already exist!");
        subjectGroup.setSubject(subject);
        subjectGroup.setProfessor(professor);
        return subjectGroupRepo.save(subjectGroup);
    }

    public List<SubjectGroupModel> getSubjectGroup(Long idSubject) throws NotFoundException {
        SubjectEntity subject = subjectRepo.findByIdSubject(idSubject);
        if (subject == null) throw new NotFoundException("No such subject!");

        List<SubjectGroupModel> list =  StreamSupport.stream(subjectGroupRepo.findAllBySubject(subject).spliterator(), false).map(SubjectGroupModel::toModel)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NotFoundException("No groups 4 this subject!");
        }
        return list;
    }


   /* public StudentEntity getOne(String login) throws NotFoundException {
        StudentEntity student = studentRepo.findByLogin(login);
        if (student == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return student;
    }*/

    public Long delete(Long id) {
        subjectGroupRepo.deleteById(id);
        return id;
    }

}
