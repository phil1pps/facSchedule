package com.example.facSchedule.service;

import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.SubjectEntity;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.SubjectModel;
import com.example.facSchedule.repository.SpecialityRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;
    @Autowired
    private SpecialityRepo specialityRepo;
    @Autowired
    private StudentRepo studentRepo;

    public SubjectEntity addSubject(SubjectEntity subject, Long idSpeciality) throws NotFoundException,AlreadyExistException {
        SpecialityEntity specialityEntity = specialityRepo.findByIdSpeciality(idSpeciality);
        if (specialityEntity == null) throw new NotFoundException("No such speciality!");
        if(subjectRepo.findBySpecialityAndSubjectName(specialityEntity,subject.getSubjectName()) != null) throw new AlreadyExistException("Subject already exist on this speciality!");
        subject.setSpeciality(specialityEntity);
        return subjectRepo.save(subject);
    }

    public SubjectModel getOneSubject(Long subjectId) throws NotFoundException {
        SubjectEntity subject = subjectRepo.findByIdSubject(subjectId);
        if (subject == null) throw new NotFoundException("No such subject");
        return subject.toModel();
    }

    public SubjectEntity editSubject(Long subjectId, SubjectEntity newSubject) throws NotFoundException,AlreadyExistException {
        SubjectEntity subject = subjectRepo.findByIdSubject(subjectId);
        if (subject == null) throw new NotFoundException("No such subject");
        SpecialityEntity speciality = subject.getSpeciality();
        String newSubjectName = newSubject.getSubjectName();
        if((!subject.getSubjectName().equals(newSubjectName)) &&
                (subjectRepo.countAllBySubjectNameAndSpeciality(newSubjectName,speciality)!=0)) throw new AlreadyExistException("Subject with this name already exist on this speciality!");
        subject.setSubjectName(newSubjectName);
        subject.setCourse(newSubject.getCourse());
        return subjectRepo.save(subject);
    }

    public Long delete(Long id) {
        subjectRepo.deleteById(id);
        return id;
    }

    public List<SubjectModel> getSpecialitySubjects(Long idSpeciality) throws NotFoundException {
        SpecialityEntity specialityEntity = specialityRepo.findByIdSpeciality(idSpeciality);
        if (specialityEntity == null) throw new NotFoundException("No such speciality!");
        List<SubjectModel> list =  StreamSupport.stream(subjectRepo.findAllBySpeciality(specialityEntity).spliterator(), false).map(SubjectModel::toModel)
                .collect(Collectors.toList());
        if (list.isEmpty() || list==null) {
            throw new NotFoundException("No subjects!");
        }
        return list;
    }

    public List<SubjectModel> getSubjectsForStudent(Long idStudent) throws NotFoundException {
        StudentEntity student = studentRepo.findById(idStudent).get();
        if(student == null) throw new NotFoundException("No such student!");
        List<SubjectModel> list =  StreamSupport.stream(subjectRepo.findAllByCourseAndSpeciality(student.getCourse(), student.getSpeciality()).spliterator(), false).map(SubjectModel::toModel)
                .collect(Collectors.toList());
        if (list.isEmpty() || list==null) {
            throw new NotFoundException("No subjects!");
        }
        return list;
    }

}
