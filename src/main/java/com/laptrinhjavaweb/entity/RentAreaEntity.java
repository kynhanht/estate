package com.laptrinhjavaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "rent_area")
public class RentAreaEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -8036234703494593625L;

    @Column(name = "value")
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
