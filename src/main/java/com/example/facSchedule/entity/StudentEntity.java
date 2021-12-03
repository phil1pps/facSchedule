package com.example.facSchedule.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;
    @NotBlank(message = "studentName is mandatory")
    private String studentName;
    @NotNull
    @Min(1)
    @Min(6)
    private Integer course;
    @NotBlank(message = "login is mandatory")
    private String login;
    @NotBlank(message = "password is mandatory")
    private String password;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private SpecialityEntity speciality;

/*
    {
        "studentName":"",
        "yearOfAdmission":"",
        "login":"",
        "password":""
    }
    additional:
        SpecialityEntity
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<PickedGroupEntity> pickedGroups;

    public StudentEntity() {
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

    public void setCourse(Integer yearOfAdmission) {
        this.course = yearOfAdmission;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSpeciality(SpecialityEntity speciality) {
        this.speciality = speciality;
    }

    public SpecialityEntity getSpeciality() {
        return speciality;
    }

}
