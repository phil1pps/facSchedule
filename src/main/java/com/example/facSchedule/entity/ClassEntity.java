package com.example.facSchedule.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClass;
    private Date dayOfClass;
    private Integer numOfClass;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private SubjectGroupEntity subjectGroup;

/*
    {
        "dayOfClass":"",
        "numOfClass":""
    }
    additional:
        SubjectGroupEntity
*/

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

    public SubjectGroupEntity getSubjectGroup() {
        return subjectGroup;
    }

    public void setSubjectGroup(SubjectGroupEntity subjectGroup) {
        this.subjectGroup = subjectGroup;
    }

}
