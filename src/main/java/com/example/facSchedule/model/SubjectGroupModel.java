package com.example.facSchedule.model;

import com.example.facSchedule.entity.SubjectGroupEntity;

public class SubjectGroupModel {
    private Long idGroup;
    private String groupName;

    public static SubjectGroupModel toModel(SubjectGroupEntity subjectGroupEntity){
        SubjectGroupModel model = new SubjectGroupModel();
        model.setIdGroup(subjectGroupEntity.getIdGroup());
        model.setGroupName(subjectGroupEntity.getGroupName());
        return model;
    }

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
