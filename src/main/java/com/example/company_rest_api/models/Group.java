package com.example.company_rest_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "group_name")
    @NotEmpty
    private String groupName;

    @Column(name = "date_of_start")
    @NotNull
    private Date dateOfStart;

    @Column(name = "date_of_finish")
    @NotNull
    private Date dateOfFinish;

    @ManyToMany
//            (cascade = {DETACH,MERGE})
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public Group() {
    }

    public Group(String groupName, Date dateOfStart, Date dateOfFinish, List<Course> courses, List<Student> students) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.courses = courses;
        this.students = students;
    }

    public Group(String groupName, Date dateOfStart, Date dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}