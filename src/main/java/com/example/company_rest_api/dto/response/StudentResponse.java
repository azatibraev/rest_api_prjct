package com.example.company_rest_api.dto.response;

import com.example.company_rest_api.enums.StudyFormat;
import com.example.company_rest_api.models.Group;
import com.example.company_rest_api.models.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class StudentResponse {

    private Long id;
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;


    public StudentResponse() {
    }

    public static StudentResponse studentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
         return studentResponse;
    }

    public static List<StudentResponse> responseList(List<Student> studentList) {
        List<StudentResponse> responses = new ArrayList<>();
        for (Student student:studentList) {
            responses.add(studentResponse(student));
        }
        return responses;
    }
}
