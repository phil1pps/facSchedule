package com.example.facSchedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "PickedGroup")
public class PickedGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPickedGroup;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private SubjectGroupEntity subjectGroup;

    public PickedGroupEntity() {}

    public Long getIdPickedGroup() {
        return idPickedGroup;
    }

    public void setIdPickedGroup(Long idPickedGroup) {
        this.idPickedGroup = idPickedGroup;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public SubjectGroupEntity getSubjectGroup() {
        return subjectGroup;
    }

    public void setSubjectGroup(SubjectGroupEntity subjectGroup) {
        this.subjectGroup = subjectGroup;
    }
}
