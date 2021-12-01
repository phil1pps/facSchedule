package com.example.facSchedule.service;

import com.example.facSchedule.entity.PickedGroupEntity;
import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.SubjectGroupEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.ClassModel;
import com.example.facSchedule.repository.PickedGroupRepo;
import com.example.facSchedule.repository.SpecialityRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.SubjectGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SpecialityRepo specialityRepo;
    @Autowired
    private PickedGroupRepo pickedGroupRepo;
    @Autowired
    private SubjectGroupRepo subjectGroupRepo;

    public StudentEntity registration (StudentEntity student, Long specialityId) throws AlreadyExistException, NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        if (speciality == null) throw new NotFoundException("No such speciality!");
        if (studentRepo.findByLogin(student.getLogin()) != null) throw new AlreadyExistException("Користувач з таким логіном вже існує");
        student.setSpeciality(speciality);
        return studentRepo.save(student);
    }

    public StudentEntity getOne(String login) throws NotFoundException {
        StudentEntity student = studentRepo.findByLogin(login);
        if (student == null) throw new NotFoundException("Пользователь не найден");
        return student;
    }

    public Long delete(Long id) {
        studentRepo.deleteById(id);
        return id;
    }

    public List<SubjectGroupEntity> getAllGroups(Long idStudent) throws NotFoundException {
        StudentEntity student = studentRepo.findByIdStudent(idStudent);
        if (student == null) throw new NotFoundException("No such student!");
        Iterable<PickedGroupEntity> pickedGroup = pickedGroupRepo.findAllByStudent(student);
        List<SubjectGroupEntity> groups = new ArrayList<>();
        for (PickedGroupEntity pg : pickedGroup){
            groups.add(subjectGroupRepo.findByIdGroup(pg.getIdPickedGroup()));
        }
        if (groups.isEmpty()) throw new NotFoundException("Student haven`t enrolled yet");
        return groups;
    }

    public List<ClassModel> getClasses(Long idStudent) throws NotFoundException {
        List<ClassModel> classes = new ArrayList<>();
        for(SubjectGroupEntity se : getAllGroups(idStudent)){
            classes.addAll(se.getClasses().stream().map(ClassModel::toModel).collect(Collectors.toList()));
        }
        if (classes.isEmpty()) throw new NotFoundException("Student haven`t classes");
        return classes;
    }

}
