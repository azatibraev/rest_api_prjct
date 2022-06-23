package com.example.company_rest_api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class CourseRequest {

    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String courseName;

    @NotNull
    private Integer duration;

    private Long companyId;
}
