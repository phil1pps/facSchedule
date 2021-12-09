package com.example.facSchedule.entity;

import com.example.facSchedule.model.ProfessorModel;
import com.example.facSchedule.model.StudentModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "Professor")
public class ProfessorEntity extends Users {

    @NotBlank(message = "professorName is mandatory")
    private String professorName;
/*
    {
        "professorName":"",
        "username":"",
        "password":""
    }
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    List<SubjectGroupEntity> groups;

    public ProfessorEntity() {

    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public List<SubjectGroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<SubjectGroupEntity> groups) {
        this.groups = groups;
    }

    public ProfessorModel toModel() {
        return ProfessorModel.toModel(this);
    }

}

