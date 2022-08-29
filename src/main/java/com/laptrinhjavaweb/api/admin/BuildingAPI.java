package com.laptrinhjavaweb.api.admin;


import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequest;
import com.laptrinhjavaweb.dto.respone.StaffResponse;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@ModelAttribute BuildingDTO buildingDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.createBuilding(buildingDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@PathVariable Long id, @ModelAttribute BuildingDTO buildingDTO){

        return ResponseEntity.ok(buildingService.updateBuilding(id, buildingDTO));
    }


    @DeleteMapping
    public ResponseEntity<Void> removeBuildings(@RequestBody List<Long> ids){

        if(ids!=null && !ids.isEmpty()){
            buildingService.deleteBuildings(ids);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assignment-building")
    public ResponseEntity<Void> assignBuilding(@RequestBody AssignmentBuildingRequest request){

        buildingService.assignBuilding(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseEntity<List<StaffResponse>> loadStaff(@PathVariable  Long buildingId){

        return ResponseEntity.ok(userService.getStaffsByBuildingId(buildingId));
    }


}
