package com.example.company_rest_api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class GroupRequest {

    @NotEmpty
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String groupName;

    @NotNull
    private Date dateOfStart;

    @NotNull
    private Date dateOfFinish;

    private List<Long> courseId = new ArrayList<>();

    public GroupRequest(String groupName, Date dateOfStart, Date dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }
}
