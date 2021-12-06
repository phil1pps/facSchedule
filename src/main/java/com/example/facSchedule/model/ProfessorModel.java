package com.example.facSchedule.model;

import com.example.facSchedule.entity.ProfessorEntity;

public class ProfessorModel {
    private Long idProfessor;
    private String ProfessorName;
    private String login;

    public static ProfessorModel toModel(ProfessorEntity professorEntity) {
        ProfessorModel model = new ProfessorModel();
        model.setIdProfessor(professorEntity.getId());
        model.setProfessorName(professorEntity.getProfessorName());
        model.setLogin(professorEntity.getUsername());
        return model;
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


}
