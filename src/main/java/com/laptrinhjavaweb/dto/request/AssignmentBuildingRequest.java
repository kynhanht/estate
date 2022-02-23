package com.laptrinhjavaweb.dto.request;

import java.io.Serializable;
import java.util.List;

public class AssignmentBuildingRequest implements Serializable{

    private List<Long> staffIds;

    private Long buildingId;

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
