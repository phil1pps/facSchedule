package com.example.facSchedule.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public void setDayOfClass(String dayOfClass) throws ParseException {
        this.dayOfClass = new SimpleDateFormat( "yyyyMMdd" ).parse( dayOfClass );
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
