package com.laptrinhjavaweb.controller.admin;


import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchRequest;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("customersControllerAdmin")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView getCustomers(@ModelAttribute(SystemConstants.MODEL_SEARCH) CustomerSearchRequest request, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        // Check role is Staff
        if (SecurityUtils.getAuthorities().contains(SystemConstants.STAFF_ROLE)) {
            Long staffId = SecurityUtils.getPrincipal().getId();
            request.setStaffId(staffId);
        }
        request.setPage(DisplayTagUtils.getPage(request, httpServletRequest));
        request.setSortColumnName(DisplayTagUtils.getSortColumnName(request, httpServletRequest));
        request.setSortDirection(DisplayTagUtils.getSortDirection(request, httpServletRequest));
        mav.addObject(SystemConstants.MODEL_SEARCH, request);
        mav.addObject(SystemConstants.MODEL_RESPONSE, customerService.searchCustomers(request));
        mav.addObject(SystemConstants.MODEL_STAFFS, userService.getStaffs());
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView createBuilding(@ModelAttribute(SystemConstants.MODEL) CustomerDTO customerDTO) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject(SystemConstants.MODEL, customerDTO);
        return mav;
    }

    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateBuilding(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject(SystemConstants.MODEL, customerService.getCustomerById(id));
        mav.addObject(SystemConstants.MODEL_TRANSACTION_TYPES, transactionService.getTransactionsByCustomerId(id));
        return mav;
    }
}
