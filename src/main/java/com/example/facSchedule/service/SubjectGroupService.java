package com.example.facSchedule.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.facSchedule.entity.*;
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

    @Autowired
    private ClassService classService;

    private Date plusDays(Date cur, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cur);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    public void generateSchedule(String dateOfStart) throws ParseException, NotFoundException, AlreadyExistException {
        Date dateStart = new SimpleDateFormat( "yyyyMMdd" ).parse( dateOfStart );
        List<SubjectGroupModel> list =  StreamSupport.stream(subjectGroupRepo.findAll().spliterator(), false).map(SubjectGroupModel::toModel).collect(Collectors.toList());
        int countNumOfClass = 1;
        Date dayOfClass = dateStart;
        for(int i = 0; i<list.size(); i++,countNumOfClass++){
            if(countNumOfClass >= 6) {
                countNumOfClass=1;
                dayOfClass = plusDays(dayOfClass, 1);
            }
            for(int week = 0; week<17; week++){
                ClassEntity classE = new ClassEntity();
                classE.setDayOfClass(plusDays(dayOfClass, week*7));
                classE.setNumOfClass(countNumOfClass);
                classService.addClass(classE,list.get(i).getIdGroup());
            }
        }
    }

    public SubjectGroupEntity addGroupToSubject(SubjectGroupEntity subjectGroup, Long idSubject, Long idProfessor) throws AlreadyExistException, NotFoundException {
        ProfessorEntity professor = professorRepo.findById(idProfessor).get();
        SubjectEntity subject = subjectRepo.findByIdSubject(idSubject);
        if (professor == null) throw new NotFoundException("No such professor!");
        if (subject == null) throw new NotFoundException("No such subject!");
        if (subjectGroupRepo.findByGroupNameAndSubject(subjectGroup.getGroupName(), subject) != null) throw new AlreadyExistException("Group with this name for this subject already exist!");
        subjectGroup.setSubject(subject);
        subjectGroup.setProfessor(professor);
        return subjectGroupRepo.save(subjectGroup);
    }

    public SubjectGroupEntity editSubjectGroup(Long idGroup, SubjectGroupEntity newSubjectGroup) throws AlreadyExistException, NotFoundException {
        SubjectGroupEntity subjectGroup = subjectGroupRepo.findByIdGroup(idGroup);
        if(subjectGroup==null) throw new NotFoundException("No such group with this id!");
        String newSubjectGroupName = newSubjectGroup.getGroupName();
        if(subjectGroupRepo.countAllByGroupNameAndSubject(newSubjectGroupName,subjectGroup.getSubject())!=0) throw new AlreadyExistException("Group with this name for this subject already exist!");
        subjectGroup.setGroupName(newSubjectGroupName);
        return subjectGroupRepo.save(subjectGroup);
    }

    public List<SubjectGroupModel> getSubjectGroups(Long idSubject) throws NotFoundException {
        SubjectEntity subject = subjectRepo.findByIdSubject(idSubject);
        if (subject == null) throw new NotFoundException("No such subject!");

        List<SubjectGroupModel> list =  StreamSupport.stream(subjectGroupRepo.findAllBySubject(subject).spliterator(), false).map(SubjectGroupModel::toModel)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NotFoundException("No groups 4 this subject!");
        }
        return list;
    }


    public SubjectGroupModel getOneSubjectGroup(Long idGroup) throws NotFoundException {
        SubjectGroupEntity subjectGroup = subjectGroupRepo.findByIdGroup(idGroup);
        if(subjectGroup==null) throw new NotFoundException("No such subject!");
        return subjectGroup.toModel();
    }

    public Long delete(Long id) {
        subjectGroupRepo.deleteById(id);
        return id;
    }

}
