package com.example.facSchedule.entity;

import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.model.SubjectModel;

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
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "speciality")
    List<SubjectEntity> subjects;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "speciality")
    List<StudentEntity> students;
    public SpecialityEntity() {
    }

    public SpecialityEntity(String specialityName) {
        this.specialityName = specialityName;
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

    public SpecialityModel toModel() {
        return SpecialityModel.toModel(this);
    }
}

