package com.example.company_rest_api.dto.response;

import com.example.company_rest_api.models.Company;
import com.example.company_rest_api.models.Course;
import com.example.company_rest_api.models.Group;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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
public class CourseResponse {

    private Long id;

    @NotEmpty
    private String courseName;

    @NotNull
    private Integer duration;

    private Integer amountOfGroups;

    public CourseResponse() {
    }

    public static CourseResponse courseResponse(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setAmountOfGroups(course.getGroups().size());
        return courseResponse;
    }

    public static List<CourseResponse> responseList(List<Course> courseList) {
        List<CourseResponse> responses = new ArrayList<>();
        for (Course course:courseList) {
            responses.add(courseResponse(course));
        }
        return responses;
    }
}
