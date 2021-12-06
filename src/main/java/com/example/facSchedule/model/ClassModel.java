package com.example.facSchedule.model;

import com.example.facSchedule.entity.ClassEntity;

import java.util.Date;

public class ClassModel {

    private Long idClass;
    private Date dayOfClass;
    private Integer numOfClass;

    public static ClassModel toModel(ClassEntity classEntity) {
        ClassModel model = new ClassModel();
        model.setIdClass(classEntity.getIdClass());
        model.setDayOfClass(classEntity.getDayOfClass());
        model.setNumOfClass(classEntity.getNumOfClass());

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
}
