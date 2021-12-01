package com.example.facSchedule.entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "SubjectGroup")
public class SubjectGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroup;
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;

/*
    {
        "groupName":""
    }
    additional:
        ProfessorEntity
        SubjectEntity
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectGroup")
    private List<ClassEntity> classes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectGroup")
    private List<PickedGroupEntity> pickedGroups;

    public SubjectGroupEntity() { }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ProfessorEntity getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorEntity professor) {
        this.professor = professor;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }
}
