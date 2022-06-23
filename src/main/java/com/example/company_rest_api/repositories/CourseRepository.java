package com.example.company_rest_api.repositories;

import com.example.company_rest_api.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Azat Ibraev
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
