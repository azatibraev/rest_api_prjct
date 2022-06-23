package com.example.company_rest_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @SequenceGenerator(name = "teacher_seq")
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

    @OneToOne
           (cascade = {MERGE, DETACH, REFRESH})
    @JsonIgnore
//    , DETACH, REFRESH
    @JoinColumn(name = "course_id")
    private Course course;


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}