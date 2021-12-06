package com.example.facSchedule.model;

import com.example.facSchedule.entity.StudentEntity;

public class StudentModel {
    private Long idStudent;
    private String studentName;
    private Integer course;
    private String login;

    public static StudentModel toModel(StudentEntity studentEntity) {
        StudentModel model = new StudentModel();
        model.setIdStudent(studentEntity.getId());
        model.setStudentName(studentEntity.getStudentName());
        model.setCourse(studentEntity.getCourse());
        model.setLogin(studentEntity.getUsername());
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

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
