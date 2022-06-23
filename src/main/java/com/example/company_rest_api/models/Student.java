package com.example.company_rest_api.models;

import com.example.company_rest_api.enums.StudyFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;


    @Column(name = "study_format")
    @NotNull
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {MERGE})
//    , DETACH, REFRESH
    @JsonIgnore
    private Group group;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, StudyFormat studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studyFormat = studyFormat;
    }
}