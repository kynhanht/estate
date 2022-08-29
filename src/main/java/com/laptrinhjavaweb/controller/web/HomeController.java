package com.laptrinhjavaweb.controller.web;

import com.laptrinhjavaweb.constant.SystemConstants;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String target = determineTarget(request, authentication);
		ModelAndView mav = new ModelAndView(target);
		return mav;
	}

	@RequestMapping(value = "/errors", method = RequestMethod.GET)
	public ModelAndView errorPage() {
		ModelAndView mav = new ModelAndView("error");
		return mav;
	}

	private String determineTarget(HttpServletRequest request, Authentication authentication) {
		String target = "";
		// When not login or error
		if(authentication == null
				|| request.getParameter("incorrectAccount") !=null
				|| request.getParameter("accessDenied") != null
				|| request.getParameter("expired") != null
				|| request.getParameter("logout") != null){
			target = "login";
		}
		// When login
		else{
			List<String> roles = SecurityUtils.getAuthorities();
			if (roles.contains(SystemConstants.STAFF_ROLE)
					|| roles.contains(SystemConstants.MANAGER_ROLE)) {
				target = "redirect:/admin/home";
			} else {
				target = "login";
			}
		}
		return target;
	}

}
