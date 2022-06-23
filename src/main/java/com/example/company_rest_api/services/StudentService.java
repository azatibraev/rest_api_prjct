package com.example.company_rest_api.services;

import com.example.company_rest_api.dto.request.StudentRequest;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.dto.response.StudentResponse;
import com.example.company_rest_api.models.Student;
import com.example.company_rest_api.repositories.GroupRepository;
import com.example.company_rest_api.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<StudentResponse> findAllStudents() {
        return StudentResponse.responseList(studentRepository.findAll());
    }

    public StudentResponse findByStudentId(Long studentId) {
        Student studentById = getStudentById(studentId);
        return StudentResponse.studentResponse(studentById);
    }

    public StudentResponse save(StudentRequest studentRequest) {
        Student student = new Student(
                studentRequest.getFirstName(),
                studentRequest.getLastName(),
                studentRequest.getEmail(),
                studentRequest.getStudyFormat());
        student.setGroup(groupRepository.findById(studentRequest.getGroupId())
                .orElseThrow(()-> new NotFoundException(
                        "Student with id = " + studentRequest.getGroupId() + "Not found")));
        studentRepository.save(student);
        return StudentResponse.studentResponse(student);
    }

    public SimpleResponse deleteByStudentId(Long studentId) {
        boolean existsById = studentRepository.existsById(studentId);
        if(!existsById) {
            throw new NotFoundException(
                    "Student with id = " + studentId + " not found!"
            );
        }
        studentRepository.deleteById(studentId);
        return new SimpleResponse(
                "DELETED",
                "Successfully delete student"
        );
    }
@Transactional
    public StudentResponse updateStudentById(Long studentId, StudentRequest studentRequest) {
        Student student = getStudentById(studentId);
        student.setGroup(groupRepository.findById(studentRequest.getGroupId())
                .orElseThrow(()-> new NotFoundException(
                        "Student with id = " + studentRequest.getGroupId() + "Not found")));
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setStudyFormat(studentRequest.getStudyFormat());

        return StudentResponse.studentResponse(student);
    }

    private Student getStudentById(Long studentId){
        return studentRepository.findById(studentId).
                orElseThrow(()-> new NotFoundException(
                        "Student with id = " + studentId + " not found"
                ));
    }
}
