package com.laptrinhjavaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {

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

    public Double getFloorArea() {
        return floorArea;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public String getLevel() {
        return level;
    }

    public Double getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Double getRentPriceTo() {
        return rentPriceTo;
    }

    public Double getRentAreaFrom() {
        return rentAreaFrom;
    }

    public Double getRentAreaTo() {
        return rentAreaTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Long getStaffId() {
        return staffId;
    }

    public List<String> getBuildingTypes() {
        return buildingTypes;
    }

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.floorArea = builder.floorArea;
        this.districtCode = builder.districtCode;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.direction = builder.direction;
        this.level = builder.level;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentAreaTo = builder.rentAreaTo;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.staffId = builder.staffId;
        this.buildingTypes = builder.buildingTypes;
    }

    public static class Builder{

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

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFloorArea(Double floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setRentPriceFrom(Double rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Double rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setRentAreaFrom(Double rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentAreaTo(Double rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setBuildingTypes(List<String> buildingTypes) {
            this.buildingTypes = buildingTypes;
            return  this;
        }

        public BuildingSearchBuilder build(){
            return new BuildingSearchBuilder(this);
        }
    }


}
