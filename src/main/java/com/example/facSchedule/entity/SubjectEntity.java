package com.example.facSchedule.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubject;
    private String subjectName;
    private Integer course;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private SpecialityEntity speciality;

/*
    {
        "subjectName":"",
        "course":""
    }
    additional:
        SpecialityEntity
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<SubjectGroupEntity> groups;

    public SubjectEntity() { }

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

    public SpecialityEntity getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityEntity speciality) {
        this.speciality = speciality;
    }

    public List<SubjectGroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(List<SubjectGroupEntity> groups) {
        this.groups = groups;
    }
}
