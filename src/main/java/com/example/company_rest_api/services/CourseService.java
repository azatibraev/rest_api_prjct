package com.example.company_rest_api.services;

import com.example.company_rest_api.dto.request.CourseRequest;
import com.example.company_rest_api.dto.response.CourseResponse;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.exceptions.CompanyNotFoundException;
import com.example.company_rest_api.exceptions.CourseNotFoundException;
import com.example.company_rest_api.models.Course;
import com.example.company_rest_api.repositories.CompanyRepository;
import com.example.company_rest_api.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;


    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }


    public List<CourseResponse> findAllCourses() {
            return CourseResponse.responseList(courseRepository.findAll());
        }

    public CourseResponse findByCourseId(Long courseId) {
        Course courseById = getCourseById(courseId);
        return CourseResponse.courseResponse(courseById);
    }

    public CourseResponse save(CourseRequest courseRequest) {
        Course course = new Course(
                courseRequest.getCourseName(),
                courseRequest.getDuration()
        );
        course.setCompany(companyRepository.findById(courseRequest.getCompanyId())
                .orElseThrow(()-> new CompanyNotFoundException(
                        "Company with id = " + courseRequest.getCompanyId() + " not found"
                )));
        courseRepository.save(course);
        return CourseResponse.courseResponse(course);
    }

    public SimpleResponse deleteByCourseId(Long courseId) {
       boolean existsById = courseRepository.existsById(courseId);
        if(!existsById) {
            throw new CourseNotFoundException(
                    "Course with id = " + courseId + " not found!"
            );
        }
        courseRepository.deleteById(courseId);
        return new SimpleResponse(
                "DELETED",
                "Successfully delete course"
        );
    }
@Transactional
    public CourseResponse updateCourseById(Long courseId, CourseRequest courseRequest) {
        Course course = getCourseById(courseId);
        course.setCompany(companyRepository.findById(courseRequest.getCompanyId())
                .orElseThrow(()-> new CompanyNotFoundException(
                        "Company with id = " + courseRequest.getCompanyId() + " not found")));
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
            return CourseResponse.courseResponse(course);
        }

    private Course getCourseById(Long courseId){
        return courseRepository.findById(courseId).
                orElseThrow(()-> new CourseNotFoundException(
                        "Course with id = " + courseId + " not found"
                ));
    }
}
