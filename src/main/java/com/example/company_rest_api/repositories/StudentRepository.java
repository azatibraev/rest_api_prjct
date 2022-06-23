package com.example.company_rest_api.repositories;

import com.example.company_rest_api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Azat Ibraev
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT COUNT(s) FROM Student s WHERE s.id=?1")
    Long countByStudentId(Long studentId);
}
