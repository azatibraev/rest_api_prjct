package com.example.company_rest_api.services;

import com.example.company_rest_api.dto.request.TeacherRequest;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.dto.response.TeacherResponse;
import com.example.company_rest_api.models.Course;
import com.example.company_rest_api.models.Teacher;
import com.example.company_rest_api.repositories.CourseRepository;
import com.example.company_rest_api.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Azat Ibraev
 */
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public List<TeacherResponse> findAllTeachers() {
        return TeacherResponse.responseList(teacherRepository.findAll());
    }

    public TeacherResponse findByTeacherId(Long teacherId) {
        Teacher teacherById = getTeacherById(teacherId);
        return TeacherResponse.teacherResponse(teacherById);
    }

    public TeacherResponse save(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher(
                teacherRequest.getFirstName(),
                teacherRequest.getLastName(),
                teacherRequest.getEmail()
        );
        teacher.setCourse(courseRepository.findById(teacherRequest.getCourseId())
                .orElseThrow(()-> new NotFoundException(
                        "Course with id = " + teacherRequest.getCourseId() + "Not found"
                )));
        teacherRepository.save(teacher);
        return TeacherResponse.teacherResponse(teacher);

    }

    public SimpleResponse deleteByTeacherId(Long teacherId) {
        boolean existsById = teacherRepository.existsById(teacherId);
        if(!existsById) {
            throw new NotFoundException(
                    "Teacher with id = " + teacherId + " not found!"
            );
        }
        teacherRepository.deleteById(teacherId);
        return new SimpleResponse(
                "DELETED",
                "Successfully delete teacher"
        );
    }

    @Transactional
    public TeacherResponse updateTeacherById(Long teacherId, TeacherRequest teacherRequest) {
        Teacher teacher = getTeacherById(teacherId);
        teacher.setCourse(courseRepository.findById(teacherRequest.getCourseId())
                .orElseThrow(()-> new NotFoundException(
                        "Course with id = " + teacherRequest.getCourseId() + "Not found"
                )));
        teacher.setFirstName(teacherRequest.getFirstName());
        teacher.setLastName(teacherRequest.getLastName());
        teacher.setEmail(teacherRequest.getEmail());
        return TeacherResponse.teacherResponse(teacher);
    }

    private Teacher getTeacherById(Long teacherId){
        return teacherRepository.findById(teacherId).
                orElseThrow(()-> new NotFoundException(
                        "Teacher with id = " + teacherId + " not found"
                ));
    }
}
