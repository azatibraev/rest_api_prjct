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

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "course_name")
    @NotEmpty
    private String courseName;

    @Column(name = "duration")
    @NotNull
    private Integer duration;

    @ManyToOne
            (cascade = {MERGE, DETACH, REFRESH})
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;


    @ManyToMany(mappedBy = "courses", cascade = REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Group> groups = new ArrayList<>();

    @OneToOne(mappedBy = "course", cascade = REMOVE, orphanRemoval = true)
    @JsonIgnore
    private Teacher teacher;

    public Course() {
    }

    public Course(String courseName, Integer duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}