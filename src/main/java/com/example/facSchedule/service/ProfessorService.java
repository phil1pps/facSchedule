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


    public String getClassesForDay(Long idProfessor, int numOfDay) throws NotFoundException, ParseException {
        List<ClassModel> allClasses = getClassesForWeekForProfessor(idProfessor);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = new SimpleDateFormat( "yyyyMMdd" ).parse( dateFormat.format(new Date()));
        List<ClassModel> result = allClasses.stream().filter(i -> i.getDayOfClass().compareTo(addToDay(today,numOfDay))==0).collect(Collectors.toList());

        ArrayList<String> finalRes = new ArrayList<>();
        for (ClassModel cm : result) {
            String adds = "";
            if(cm.getNumOfClass()==1)adds="*8:30-9:50*";
            else if (cm.getNumOfClass()==2)adds="*10:00-11:20*";
            else if (cm.getNumOfClass()==3)adds="*11:40-13:00*";
            else if (cm.getNumOfClass()==4)adds="*13:30-14:50*";
            else if (cm.getNumOfClass()==5)adds="*15:00-16:20*";
            else if (cm.getNumOfClass()==6)adds="*16:30-17:50*";
            else if (cm.getNumOfClass()==7)adds="*18:00-19:20*";
            finalRes.add( adds +" subject:" + cm.getSubjectName() + " group:" +  cm.getGroupName() +'\n');
        }
        finalRes.stream().sorted().collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(addToDay(today,numOfDay).toString().substring(0,10)+'\n');
        for (String cm : finalRes) {
            sb.append(cm);
        }

        return sb.toString();
    }

    private Date addToDay(Date today, int dayToAdd){
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE, dayToAdd);
        return cal.getTime();
    }

    private Date addNextWeekDay(Date today){
        return addToDay(today,7);
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