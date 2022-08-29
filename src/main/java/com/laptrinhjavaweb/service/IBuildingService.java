package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.respone.BuildingSearchResponse;
import com.laptrinhjavaweb.dto.respone.PaginationResponse;

import java.util.List;

public interface IBuildingService {

    PaginationResponse<BuildingSearchResponse> searchBuildings(BuildingSearchRequest request);

    BuildingDTO getBuildingById(Long id);

    BuildingDTO createBuilding(BuildingDTO buildingDTO);

    BuildingDTO updateBuilding(Long id, BuildingDTO buildingDTO);

    void deleteBuildings(List<Long> ids);

    void assignBuilding(AssignmentBuildingRequest request);
}
