package com.example.company_rest_api.dto.response;

import com.example.company_rest_api.models.Group;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class GroupResponse {

    private Long id;

    @NotEmpty
    private String groupName;

    @NotNull
    private Date dateOfStart;

    @NotNull
    private Date dateOfFinish;

    private Integer sizeOfStudents;

    public GroupResponse() {
    }


    public static GroupResponse groupResponse(Group group) {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setDateOfFinish(group.getDateOfFinish());
        groupResponse.setSizeOfStudents(group.getStudents().size());
        return groupResponse;
    }

    public static List<GroupResponse> responseList(List<Group> groupList) {
        List<GroupResponse> responses = new ArrayList<>();
        for (Group group:groupList) {
            responses.add(groupResponse(group));
        }
        return responses;
    }
}