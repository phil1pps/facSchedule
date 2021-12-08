package com.example.facSchedule.entity;

import com.example.facSchedule.model.StudentModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "Student")
public class StudentEntity extends Users {

    @NotEmpty(message = "Speciality name is mandatory")
    private String studentName;
    @Min(1)
    @Max(6)
    private Integer course;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private SpecialityEntity speciality;

/*
    {
        "studentName":"",
        "yearOfAdmission":"",
        "username":"",
        "password":""
    }
    additional:
        SpecialityEntity
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<PickedGroupEntity> pickedGroups;

    public StudentEntity() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer yearOfAdmission) {
        this.course = yearOfAdmission;
    }

    public void setSpeciality(SpecialityEntity speciality) {
        this.speciality = speciality;
    }

    public SpecialityEntity getSpeciality() {
        return speciality;
    }

    public StudentModel toModel() {
        return new StudentModel();
    }

}
