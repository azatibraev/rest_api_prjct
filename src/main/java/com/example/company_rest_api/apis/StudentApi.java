package com.example.company_rest_api.apis;

import com.example.company_rest_api.dto.request.StudentRequest;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.dto.response.StudentResponse;
import com.example.company_rest_api.models.Student;
import com.example.company_rest_api.services.GroupService;
import com.example.company_rest_api.services.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public List<StudentResponse> findAllStudents() {
        return studentService.findAllStudents();
    }

    // findById
    @GetMapping("/find/{studentId}")
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findByStudentId(studentId);
    }

    //save
    @PostMapping("/save")
    public StudentResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    //delete
    @DeleteMapping("/delete/{studentId}")
    public SimpleResponse deleteByStudentId(@PathVariable Long studentId) {
        return studentService.deleteByStudentId(studentId);
    }

    //update
    @PutMapping("/update/{studentId}")
    public StudentResponse updateStudentById(@PathVariable Long studentId,
                                   @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudentById(studentId,studentRequest);
    }
}
