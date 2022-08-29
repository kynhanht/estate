package com.laptrinhjavaweb.dto.request;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchRequest extends PaginationRequest{


    private String name;
    private Double floorArea;
    private String districtCode;
    private String ward;
    private String street;
    private Integer numberOfBasement;
    private String direction;
    private String level;
    private Double rentPriceFrom;
    private Double rentPriceTo;
    private Double rentAreaFrom;
    private Double rentAreaTo;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> buildingTypes = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Double floorArea) {
        this.floorArea = floorArea;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getRentPriceFrom() {
        return rentPriceFrom;
    }

    public void setRentPriceFrom(Double rentPriceFrom) {
        this.rentPriceFrom = rentPriceFrom;
    }

    public Double getRentPriceTo() {
        return rentPriceTo;
    }

    public void setRentPriceTo(Double rentPriceTo) {
        this.rentPriceTo = rentPriceTo;
    }

    public Double getRentAreaFrom() {
        return rentAreaFrom;
    }

    public void setRentAreaFrom(Double rentAreaFrom) {
        this.rentAreaFrom = rentAreaFrom;
    }

    public Double getRentAreaTo() {
        return rentAreaTo;
    }

    public void setRentAreaTo(Double rentAreaTo) {
        this.rentAreaTo = rentAreaTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<String> getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(List<String> buildingTypes) {
        this.buildingTypes = buildingTypes;
    }
}
