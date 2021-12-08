package com.example.facSchedule.service;

import com.example.facSchedule.entity.PickedGroupEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.SubjectGroupEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.repository.PickedGroupRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.SubjectGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickedGroupService {

    private PickedGroupRepo pickedGroupRepo;
    @Autowired
    public PickedGroupService(PickedGroupRepo pickedGroupRepo){
        this.pickedGroupRepo = pickedGroupRepo;
    }

    private StudentRepo studentRepo;
    @Autowired
    public void setDependencyB(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Autowired
    private SubjectGroupRepo subjectGroupRepo;


    public PickedGroupEntity studentEnrollsGroup(Long idStudent, Long idSubjectGroup) throws NotFoundException, AlreadyExistException {
        PickedGroupEntity pickedGroup = new PickedGroupEntity();
        StudentEntity student = studentRepo.findById(idStudent).get();
        SubjectGroupEntity subjectGroup = subjectGroupRepo.findByIdGroup(idSubjectGroup);
        if (student == null) throw new NotFoundException("No such student!");
        if (subjectGroup == null) throw new NotFoundException("No such subjectGroup!");
        // Перевірка на унікальність
        if(pickedGroupRepo.findByStudentAndSubjectGroup(student,subjectGroup) != null) throw new AlreadyExistException("Student already enroll in this group");
        pickedGroup.setStudent(student);
        pickedGroup.setSubjectGroup(subjectGroup);
        return pickedGroupRepo.save(pickedGroup);
    }

}
