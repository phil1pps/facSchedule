package com.example.facSchedule.service;

import com.example.facSchedule.entity.ClassEntity;
import com.example.facSchedule.entity.SubjectGroupEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.repository.ClassRepo;
import com.example.facSchedule.repository.SubjectGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClassService {

    @Autowired
    private ClassRepo classRepo;
    @Autowired
    private SubjectGroupRepo subjectGroupRepo;

    public ClassEntity addClass(ClassEntity Class, Long subjectGroupId) throws AlreadyExistException, NotFoundException {
        SubjectGroupEntity subjectGroup = subjectGroupRepo.findByIdGroup(subjectGroupId);
        if(subjectGroup==null) throw new NotFoundException("No such group!");
        Date fdoc = Class.getDayOfClass();
        Integer fnoc = Class.getNumOfClass();
        for (ClassEntity ce : subjectGroup.getClasses()) {
            if((ce.getDayOfClass() == fdoc) && (ce.getNumOfClass() == fnoc)) throw new AlreadyExistException("Class already exist for this time on this group!");
        }
        Class.setSubjectGroup(subjectGroup);
        return classRepo.save(Class);
    }


    public Long delete(Long id) {
        classRepo.deleteById(id);
        return id;
    }

}
