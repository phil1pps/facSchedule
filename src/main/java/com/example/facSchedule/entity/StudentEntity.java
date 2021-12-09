package com.example.facSchedule.entity;

import com.example.facSchedule.model.StudentModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Student")
public class StudentEntity extends Users {

    @NotBlank(message = "studentName is mandatory")
    private String studentName;
    @NotNull
    @Min(1)
    @Min(6)
    private Integer course;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private SpecialityEntity speciality;

/*
    {
        "studentName":"",
        "course":"",
        "login":"",
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
        return StudentModel.toModel(this);
    }

}
