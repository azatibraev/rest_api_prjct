package com.example.company_rest_api.dto.response;

import com.example.company_rest_api.models.Student;
import com.example.company_rest_api.models.Teacher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class TeacherResponse {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    public TeacherResponse() {}

    public static TeacherResponse teacherResponse(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setFirstName(teacher.getFirstName());
        teacherResponse.setLastName(teacher.getLastName());
        teacherResponse.setEmail(teacher.getEmail());
        return teacherResponse;
    }

    public static List<TeacherResponse> responseList(List<Teacher> teacherList) {
        List<TeacherResponse> responses = new ArrayList<>();
        for (Teacher teacher:teacherList) {
            responses.add(teacherResponse(teacher));
        }
        return responses;
    }
}