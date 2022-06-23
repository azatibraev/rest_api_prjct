package com.example.company_rest_api.apis;

import com.example.company_rest_api.dto.request.CourseRequest;
import com.example.company_rest_api.dto.response.CourseResponse;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.models.Company;
import com.example.company_rest_api.models.Course;
import com.example.company_rest_api.services.CompanyService;
import com.example.company_rest_api.services.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Azat Ibraev
 */
@RestController
@RequestMapping("api/courses")
@CrossOrigin
@Tag(name = "Course API")
public class CourseApi {

    private final CourseService courseService;
    private final CompanyService companyService;


    public CourseApi(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }

    //findAll
    @GetMapping("/findAll")
    public List<CourseResponse> findAllCourses() {
        return courseService.findAllCourses();
    }

    // findById
    @GetMapping("/find/{courseId}")
    public CourseResponse findById(@PathVariable Long courseId) {
        return courseService.findByCourseId(courseId);
    }

    //save
    @PostMapping("/save")
    public CourseResponse save(@RequestBody CourseRequest courseRequest) {
        return courseService.save(courseRequest);
    }

    //delete
    @DeleteMapping("/delete/{courseId}")
    public SimpleResponse deleteByCourseId(@PathVariable Long courseId) {
        return courseService.deleteByCourseId(courseId);
    }

    //update
    @PutMapping("/update/{courseId}")
    public CourseResponse updateCourseById(@PathVariable Long courseId,
                                     @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourseById(courseId,courseRequest);
    }
}
