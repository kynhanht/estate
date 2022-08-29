package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.request.UserSearchRequest;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IRoleService;
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

@Controller(value = "usersControllerOfAdmin")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    @RequestMapping(value = "/admin/user-list", method = RequestMethod.GET)

    public ModelAndView getUsers(@ModelAttribute(SystemConstants.MODEL_SEARCH) UserSearchRequest request, HttpServletRequest httpServletRequest) {
        ModelAndView mav = new ModelAndView("admin/user/list");
        request.setPage(DisplayTagUtils.getPage(request, httpServletRequest));
        request.setSortColumnName(DisplayTagUtils.getSortColumnName(request, httpServletRequest));
        request.setSortDirection(DisplayTagUtils.getSortDirection(request, httpServletRequest));
        mav.addObject(SystemConstants.MODEL_SEARCH, request);
        mav.addObject(SystemConstants.MODEL_RESPONSE, userService.searchUsers(request));
        return mav;
    }

    @RequestMapping(value = "/admin/user-edit", method = RequestMethod.GET)
    public ModelAndView addUser(@ModelAttribute(SystemConstants.MODEL) UserDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        mav.addObject(SystemConstants.MODEL, model);
        mav.addObject(SystemConstants.MODEL_ROLES, roleService.getRoles());
        return mav;
    }

    @RequestMapping(value = "/admin/profile-{username}", method = RequestMethod.GET)
    public ModelAndView updateProfile(@PathVariable("username") String username, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/profile");
        mav.addObject(SystemConstants.MODEL, userService.findOneByUserName(username));
        return mav;
    }

    @RequestMapping(value = "/admin/user-edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateUser(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        mav.addObject(SystemConstants.MODEL, userService.findUserById(id));
        mav.addObject(SystemConstants.MODEL_ROLES, roleService.getRoles());
        return mav;
    }

    @RequestMapping(value = "/admin/profile-password", method = RequestMethod.GET)
    public ModelAndView updatePassword(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/password");
        UserDTO model = userService.findOneByUserName(SecurityUtils.getPrincipal().getUsername());
        mav.addObject(SystemConstants.MODEL, model);
        return mav;
    }

}
