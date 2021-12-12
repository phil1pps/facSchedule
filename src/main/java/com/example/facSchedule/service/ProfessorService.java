package com.example.facSchedule.service;

import com.example.facSchedule.entity.*;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.ClassModel;
import com.example.facSchedule.model.ProfessorModel;
import com.example.facSchedule.model.SubjectGroupModel;
import com.example.facSchedule.repository.ProfessorRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public List<ClassModel> getClassesForProfessor(Long idProfessor) throws NotFoundException {
        List<ClassModel> result = new ArrayList<>();
        ProfessorEntity professor = professorRepo.findById(idProfessor).get();
        for(SubjectGroupEntity sge : professor.getGroups()){
            result.addAll(sge.getClasses().stream().map(ClassModel::toModel).collect(Collectors.toList()));
        }
        return result;
    }

    public List<ClassModel> getClassesForWeekForProfessor(Long idProfessor) throws NotFoundException, ParseException {
        List<ClassModel> allClasses = getClassesForProfessor(idProfessor);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = new SimpleDateFormat( "yyyyMMdd" ).parse( dateFormat.format(new Date()));
        List<ClassModel> result = allClasses.stream().filter(i -> i.getDayOfClass().compareTo(today)>=0 && i.getDayOfClass().compareTo(addNextWeekDay(today))<=0).collect(Collectors.toList());
        return result;
    }

    private Date addNextWeekDay(Date today){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
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