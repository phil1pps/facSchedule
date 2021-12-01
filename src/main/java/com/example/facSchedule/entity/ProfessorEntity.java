package com.example.facSchedule.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Professor")
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfessor;
    private String ProfessorName;
    private String login;
    private String password;

/*
    {
        "ProfessorName":"",
        "login":"",
        "password":""
    }
*/


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    List<SubjectGroupEntity> groups;

    public ProfessorEntity() {
    }

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getProfessorName() {
        return ProfessorName;
    }

    public void setProfessorName(String professorName) {
        ProfessorName = professorName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<SubjectGroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<SubjectGroupEntity> groups) {
        this.groups = groups;
    }
}

