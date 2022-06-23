package com.example.company_rest_api.services;

import com.example.company_rest_api.dto.request.GroupRequest;
import com.example.company_rest_api.dto.response.GroupResponse;
import com.example.company_rest_api.dto.response.SimpleResponse;
import com.example.company_rest_api.models.Course;
import com.example.company_rest_api.models.Group;
import com.example.company_rest_api.repositories.CourseRepository;
import com.example.company_rest_api.repositories.GroupRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Azat Ibraev
 */
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public GroupService(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

    public List<GroupResponse> findAllGroups() {
        return GroupResponse.responseList(groupRepository.findAll());
    }

    public GroupResponse findByGroupId(Long groupId) {
        Group groupById = getGroupById(groupId);
        return GroupResponse.groupResponse(groupById);

    }

    public GroupResponse save(GroupRequest groupRequest) {
        Group group = new Group(
                groupRequest.getGroupName(),
                groupRequest.getDateOfStart(),
                groupRequest.getDateOfFinish());
        List<Course> allById = courseRepository.findAllById(groupRequest.getCourseId());
        group.setCourses(allById);
        groupRepository.save(group);
        return GroupResponse.groupResponse(group);
    }

    public SimpleResponse deleteByGroupId(Long groupId) {
        boolean existsById = groupRepository.existsById(groupId);
        if(!existsById) {
            throw new NotFoundException(
                    "Group with id = " + groupId + " not found!"
            );
        }
        groupRepository.deleteById(groupId);
        return new SimpleResponse(
                "DELETED",
                "Successfully delete group"
        );
    }

    @Transactional

    public GroupResponse updateGroupById(Long groupId, GroupRequest groupRequest) {
        Group group = getGroupById(groupId);
        List<Course> allById = courseRepository.findAllById((groupRequest.getCourseId()));
        group.setCourses(allById);
        group.setGroupName(groupRequest.getGroupName());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setDateOfFinish(groupRequest.getDateOfFinish());
        return GroupResponse.groupResponse(group);
    }

    private Group getGroupById(Long groupId){
        return groupRepository.findById(groupId).
                orElseThrow(()-> new NotFoundException(
                        "Group with id = " + groupId + " not found"
                ));
    }
}
