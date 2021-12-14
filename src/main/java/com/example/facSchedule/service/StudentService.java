package com.example.facSchedule.service;

import com.example.facSchedule.entity.Authority;
import com.example.facSchedule.entity.SpecialityEntity;
import com.example.facSchedule.entity.StudentEntity;
import com.example.facSchedule.entity.Users;
import com.example.facSchedule.exceptions.AlreadyExistException;
import com.example.facSchedule.exceptions.NotFoundException;
import com.example.facSchedule.model.ClassModel;
import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.model.StudentModel;
import com.example.facSchedule.model.SubjectGroupModel;
import com.example.facSchedule.repository.SpecialityRepo;
import com.example.facSchedule.repository.StudentRepo;
import com.example.facSchedule.repository.UsersRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log
@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private SpecialityRepo specialityRepo;
    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private ClassService classService;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PickedGroupService pickedGroupService;

    public StudentEntity registration (StudentEntity student, Long specialityId) throws AlreadyExistException, NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        if(speciality == null)throw new NotFoundException("No speciality");
        student.setSpeciality(speciality);
        Users userFromBD = userRepo.findByUsername(student.getUsername());
        if(userFromBD != null)
            throw new AlreadyExistException("This username already registered!");

        student.setEnabled(true);
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setAuthorities(Collections.singleton(Authority.ROLE_STUDENT));
        return studentRepo.save(student);
    }

    public void transferStudentToNextYear(Long idStudent){
        StudentEntity student = studentRepo.findById(idStudent).get();
        student.setCourse(student.getCourse()+1);
        log.info("Student with id:"+idStudent+" was transferred to the course");
    }

    public List<ClassModel> getClassesForStudent(Long studentId) throws NotFoundException {
        List<ClassModel> result = new ArrayList<>();
        List<SubjectGroupModel> groups = pickedGroupService.getGroups(studentId);
        for(SubjectGroupModel sgm : groups){
            for(ClassModel cm :classService.getClassesForGroup(sgm.getIdGroup())){
                result.add(cm);
            }
        }
        return result;
    }

    public List<ClassModel> getClassesForWeekForStudent(Long studentId) throws NotFoundException, ParseException {
        List<ClassModel> allClasses = getClassesForStudent(studentId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = new SimpleDateFormat( "yyyyMMdd" ).parse( dateFormat.format(new Date()));
        List<ClassModel> result = allClasses.stream().filter(i -> i.getDayOfClass().compareTo(today)>=0 && i.getDayOfClass().compareTo(addNextWeekDay(today))<=0).collect(Collectors.toList());
        return result;
    }

    public String getClassesForDay(Long studentId, int numOfDay) throws NotFoundException, ParseException {
        try {
            List<ClassModel> allClasses = getClassesForStudent(studentId);
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
        catch (NotFoundException ex) {
            return ex.getMessage();
        }
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

    public StudentEntity editStudent(Long idStudent, StudentEntity newStudent, Long specialityId) throws NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        if(speciality == null)throw new NotFoundException("No speciality with this id");
        StudentEntity student = studentRepo.findById(idStudent).get();
        if(student==null)throw new NotFoundException("No student with this id");
        student.setStudentName(newStudent.getStudentName());
        student.setCourse(newStudent.getCourse());
        student.setSpeciality(speciality);
        return studentRepo.save(student);
    }

    public List<StudentModel> getStudentsFromSpeciality(Long specialityId) throws NotFoundException {
        SpecialityEntity speciality = specialityRepo.findByIdSpeciality(specialityId);
        if(speciality == null) throw new NotFoundException("No speciality with this id");
        List<StudentModel> list =  StreamSupport.stream(studentRepo.findAllBySpeciality(speciality).spliterator(), false).map(StudentModel::toModel)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new NotFoundException("No specialities!");
        }
        return list;
    }

    public StudentModel getOneById (Long idStudent){
        StudentEntity student = studentRepo.findById(idStudent).get();
        return student.toModel();
    }

    public Long delete(Long id) {
        studentRepo.deleteById(id);
        return id;
    }


}

