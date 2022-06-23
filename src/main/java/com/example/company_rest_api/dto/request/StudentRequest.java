package com.example.company_rest_api.dto.request;

import com.example.company_rest_api.enums.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class StudentRequest {
    @NotEmpty
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    private String email;

    @NotNull
    @Enumerated
    private StudyFormat studyFormat;

    private Long groupId;
}

