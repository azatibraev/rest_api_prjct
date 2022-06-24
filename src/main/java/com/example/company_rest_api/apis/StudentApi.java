package com.example.company_rest_api.apis;

import com.example.company_rest_api.dto.request.StudentRequest;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.dto.response.StudentResponse;
import com.example.company_rest_api.models.Student;
import com.example.company_rest_api.services.GroupService;
import com.example.company_rest_api.services.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Azat Ibraev
 */
@RestController
@RequestMapping("/api/students")
@Tag(name = "Student API")
public class StudentApi {

    private final StudentService studentService;
    private final GroupService groupService;

    public StudentApi(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    //findAll
    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority({'ADMIN'})")
    public List<StudentResponse> findAllStudents() {
        return studentService.findAllStudents();
    }

    // findById
    @GetMapping("/find/{studentId}")
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'STUDENT','TEACHER'})")
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findByStudentId(studentId);
    }

    //save
    @PostMapping("/save")
    @PreAuthorize("hasAuthority({'ADMIN'})")
    public StudentResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    //delete
    @DeleteMapping("/delete/{studentId}")
    @PreAuthorize("hasAuthority({'ADMIN'})")
    public SimpleResponse deleteByStudentId(@PathVariable Long studentId) {
        return studentService.deleteByStudentId(studentId);
    }

    //update
    @PutMapping("/update/{studentId}")
    @PreAuthorize("hasAuthority({'ADMIN'})")
    public StudentResponse updateStudentById(@PathVariable Long studentId,
                                   @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudentById(studentId,studentRequest);
    }
}
