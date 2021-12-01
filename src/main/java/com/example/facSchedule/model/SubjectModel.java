package com.example.facSchedule.model;

import com.example.facSchedule.entity.SubjectEntity;

public class SubjectModel {
    private Long idSubject;
    private String subjectName;
    private Integer course;

    public static SubjectModel toModel(SubjectEntity subjectEntity){
        SubjectModel model = new SubjectModel();
        model.setIdSubject(subjectEntity.getIdSubject());
        model.setSubjectName(subjectEntity.getSubjectName());
        model.setCourse(subjectEntity.getCourse());
        return model;
    }

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

}
