package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.request.PaginationRequest;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import com.laptrinhjavaweb.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "buildingsControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView getBuildings(@ModelAttribute(SystemConstants.MODEL_SEARCH) BuildingSearchRequest request, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        // check role is Staff
        if (SecurityUtils.getAuthorities().contains(SystemConstants.STAFF_ROLE)) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            request.setStaffId(staffId);
        }
        request.setPage(DisplayTagUtils.getPage(request, httpServletRequest));
        request.setSortColumnName(DisplayTagUtils.getSortColumnName(request, httpServletRequest));
        request.setSortDirection(DisplayTagUtils.getSortDirection(request, httpServletRequest));
        mav.addObject(SystemConstants.MODEL_SEARCH, request);
        mav.addObject(SystemConstants.MODEL_RESPONSE, buildingService.searchBuildings(request));
        mav.addObject(SystemConstants.MODEL_DISTRICTS, SystemUtils.getDistricts());
        mav.addObject(SystemConstants.MODEL_BUILDING_TYPES, SystemUtils.getBuildingTypes());
        mav.addObject(SystemConstants.MODEL_STAFFS, userService.getStaffs());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView createBuilding(@ModelAttribute(SystemConstants.MODEL) BuildingDTO buildingDTO) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject(SystemConstants.MODEL, buildingDTO);
        mav.addObject(SystemConstants.MODEL_DISTRICTS, SystemUtils.getDistricts());
        mav.addObject(SystemConstants.MODEL_BUILDING_TYPES, SystemUtils.getBuildingTypes());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateBuilding(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject(SystemConstants.MODEL, buildingService.getBuildingById(id));
        mav.addObject(SystemConstants.MODEL_DISTRICTS, SystemUtils.getDistricts());
        mav.addObject(SystemConstants.MODEL_BUILDING_TYPES, SystemUtils.getBuildingTypes());
        return mav;
    }



}
