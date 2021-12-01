package com.example.facSchedule.model;

import com.example.facSchedule.entity.StudentEntity;

public class StudentModel {
    private Long idStudent;
    private String studentName;
    private Integer yearOfAdmission;
    private String login;

    public static StudentModel toModel(StudentEntity studentEntity) {
        StudentModel model = new StudentModel();
        model.setIdStudent(studentEntity.getIdStudent());
        model.setStudentName(studentEntity.getStudentName());
        model.setYearOfAdmission(studentEntity.getCourse());
        model.setLogin(studentEntity.getLogin());
        return model;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(Integer yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
