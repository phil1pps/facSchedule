package com.example.facSchedule.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "Speciality")
public class SpecialityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSpeciality;
    @NotBlank(message = "specialityName is mandatory")
    private String specialityName;


/*
    {
        "specialityName":""
    }
     additional:
        DeaneryEntity
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "speciality")
    List<SubjectEntity> subjects;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "speciality")
    List<StudentEntity> students;
    public SpecialityEntity() {
    }

    public Long getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(Long idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public List<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        this.subjects = subjects;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }
}

