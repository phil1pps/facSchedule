package com.example.facSchedule.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Professor")
public class ProfessorEntity extends Users {

    private String professorName;
/*
    {
        "professorName":"",
        "login":"",
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
}

