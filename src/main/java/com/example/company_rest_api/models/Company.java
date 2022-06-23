package com.example.company_rest_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EnableJpaAuditing
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="company_name")
    @NotEmpty(message = "Company name shouldn't be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String companyName;

    @Column(name="located_country")
    @NotEmpty
    private String locatedCountry;


    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public Company() {
    }

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}