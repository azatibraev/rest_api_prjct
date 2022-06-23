package com.example.company_rest_api.apis;

import com.example.company_rest_api.dto.request.GroupRequest;
import com.example.company_rest_api.dto.response.GroupResponse;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.services.CourseService;
import com.example.company_rest_api.services.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Azat Ibraev
 */
@RestController
@RequestMapping("/api/groups")
@Tag(name = "Group API")
public class GroupApi {

    private final GroupService groupService;
    private final CourseService courseService;

    public GroupApi(GroupService groupService, CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    //findAll
    @GetMapping("/findAll")
    public List<GroupResponse> findAllGroups() {
        return groupService.findAllGroups();
    }

    // findById
    @GetMapping("/find/{groupId}")
    public GroupResponse findById(@PathVariable Long groupId) {
        return groupService.findByGroupId(groupId);
    }

    //save
    @PostMapping("/save/{courseId}")
    public GroupResponse save(@RequestBody GroupRequest groupRequest) {
        return groupService.save(groupRequest);
    }

    //delete
    @DeleteMapping("/delete/{groupId}")
    public SimpleResponse deleteByGroupId(@PathVariable Long groupId) {
        return groupService.deleteByGroupId(groupId);
    }

    //update
    @PutMapping("/update/{groupId}")
    public GroupResponse updateGroupById(@PathVariable Long groupId,
                                    @RequestBody GroupRequest groupRequest) {
        return groupService.updateGroupById(groupId,groupRequest);
    }
}
