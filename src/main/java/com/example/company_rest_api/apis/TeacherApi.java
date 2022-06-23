package com.example.company_rest_api.apis;

import com.example.company_rest_api.dto.request.TeacherRequest;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.dto.response.TeacherResponse;
import com.example.company_rest_api.services.CourseService;
import com.example.company_rest_api.services.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Azat Ibraev
 */
@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher API")
public class TeacherApi {

    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherApi(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    //findAll
    @GetMapping("/findAll")
    public List<TeacherResponse> findAllStudents() {
        return teacherService.findAllTeachers();
    }

    // findById
    @GetMapping("/find")
    public TeacherResponse findById(@PathVariable Long teacherId) {
        return teacherService.findByTeacherId(teacherId);
    }

    //save
    @PostMapping("/save/{courseId}")
    public TeacherResponse save(@RequestBody TeacherRequest teacherRequest) {
        return teacherService.save(teacherRequest);
    }

    //delete
    @DeleteMapping("/delete/{teacherId}")
    public SimpleResponse deleteByTeacherId(@PathVariable Long teacherId) {
        return teacherService.deleteByTeacherId(teacherId);
    }

    //update
    @PutMapping("/update/{teacherId}")
    public TeacherResponse updateStudentById(@PathVariable Long teacherId,
                                     @RequestBody TeacherRequest teacherRequest) {
        return teacherService.updateTeacherById(teacherId,teacherRequest);
    }
}
