package com.example.facSchedule.model;

import com.example.facSchedule.entity.ClassEntity;

import java.util.Date;

public class ClassModel {

    private Long idClass;
    private Date dayOfClass;
    private String day;
    private Integer numOfClass;
    private String groupName;
    private String subjectName;

    public static ClassModel toModel(ClassEntity classEntity) {
        ClassModel model = new ClassModel();
        model.setIdClass(classEntity.getIdClass());
        model.setDayOfClass(classEntity.getDayOfClass());

        model.setDay(classEntity.getDayOfClass().toString());

        model.setNumOfClass(classEntity.getNumOfClass());
        model.setGroupName(classEntity.getSubjectGroup().getGroupName());
        model.setSubjectName(classEntity.getSubjectGroup().getSubject().getSubjectName());
        return model;
    }


    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    public Date getDayOfClass() {
        return dayOfClass;
    }

    public void setDayOfClass(Date dayOfClass) {
        this.dayOfClass = dayOfClass;
    }

    public Integer getNumOfClass() {
        return numOfClass;
    }

    public void setNumOfClass(Integer numOfClass) {
        this.numOfClass = numOfClass;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
