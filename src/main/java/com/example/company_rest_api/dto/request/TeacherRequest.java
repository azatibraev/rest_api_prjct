package com.example.company_rest_api.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class TeacherRequest {

    @NotEmpty
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String lastName;

    @Email()
    private String email;

    private Long courseId;
}
