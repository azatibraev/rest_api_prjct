package com.example.company_rest_api.repositories;

import com.example.company_rest_api.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Azat Ibraev
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
